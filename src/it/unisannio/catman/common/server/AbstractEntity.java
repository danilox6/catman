package it.unisannio.catman.common.server;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

public abstract class AbstractEntity<K> {
		
	protected static EntityManager getEntityManager() {
		return ThreadLocalEntityManager.get();
	}
	
	protected static String getEntityId(Class<?> entityClass) {
		EntityManager entityManager = getEntityManager();
		
		EntityType<?> entityType = entityManager.getMetamodel().entity(entityClass);
		return entityType.getId(entityType.getIdType().getJavaType()).getName();
	}

	protected static <T, K> T find(Class<T> entityClass, K key) {
		EntityManager entityManager = getEntityManager();
		
		return entityManager.find(entityClass, key);
	}

	protected static <T, K> List<T> findAll(Class<T> entityClass, K... keys) {
		EntityManager entityManager = getEntityManager();
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(entityClass);
		Root<T> root = cq.from(entityClass);
		if(keys.length > 0) {
			In<Object> in = cb.in(root.get(getEntityId(entityClass)));
			for(K key : keys)
				in.value(key);
			cq.where(in);
		}
		
		return entityManager.createQuery(cq).getResultList();
	}

	protected static <T> int count(Class<T> entityClass) {
		EntityManager entityManager = getEntityManager();
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(entityClass)));
		return entityManager.createQuery(cq).getSingleResult().intValue();
	}

	protected static <T> List<T> list(Class<T> entityClass, int offset, int size) {
		EntityManager entityManager = getEntityManager();
		
		CriteriaQuery<T> cq = entityManager.getCriteriaBuilder().createQuery(entityClass);
		cq.from(entityClass);
		return entityManager.createQuery(cq)
				.setFirstResult(offset)
				.setMaxResults(size)
				.getResultList();
	}
	
	protected static <T extends AbstractEntity<K>, K> void deleteObjects(Class<T> entityClass, List<T> objects, List<K> exclusions) {
		EntityManager entityManager = getEntityManager();
		
		for(T obj : objects) {
			if(!exclusions.contains(obj.getId()))
				entityManager.remove(obj);
		}
	}
	
	protected static <T extends AbstractEntity<K>, K> void deleteByQuery(Class<T> entityClass, List<K> exclusions, String query, Object... params) {
		List<T> objects = findByQuery(query, params);
		deleteObjects(entityClass, objects, new ArrayList<K>());
	}

	protected static <T extends AbstractEntity<K>, K> void deleteByKeys(Class<T> entityClass, List<K> ids) {
		EntityManager entityManager = getEntityManager();
		
		String id = getEntityId(entityClass);
		TypedQuery<T> select = entityManager.createQuery("SELECT o from " + entityClass.getName() + " o WHERE " + id +" IN (:in)", entityClass);
		select.setParameter("in", ids);
		
		deleteObjects(entityClass, select.getResultList(), new ArrayList<K>());
	}
	
	private static <T> Query buildQuery(Class<T> entityClass, String prologue, String filter, Object... args) {
		EntityManager entityManager = getEntityManager();
		
		StringBuffer jpqlBuf = new StringBuffer(prologue).append(" FROM ").append(entityClass.getName()).append(" obj WHERE ").append(filter);
		Query query = entityManager.createQuery(jpqlBuf.toString());
		for(int i = 0; i < args.length; ++i) {
			query.setParameter(i + 1, args[i]);
		}
		return query;
	}
	
	protected static <T> List<T> listByQuery(Class<T> entityClass, int start, int len, String query, Object... args) {
		EntityManager entityManager = getEntityManager();
		Query q = entityManager.createQuery(query);
		for(int i = 0; i < args.length; ++i) {
			q.setParameter(i + 1, args[i]);
		}
		
		q.setFirstResult(start);
		if(len != -1)
			q.setMaxResults(len);
		
		return (List<T>) q.getResultList();
	}
	
	protected static <T> List<T> findByQuery(String query, Object... args) {
		EntityManager entityManager = getEntityManager();
		Query q = entityManager.createQuery(query);
		for(int i = 0; i < args.length; ++i) {
			q.setParameter(i + 1, args[i]);
		}
		return (List<T>) q.getResultList();
	}
	
	protected static <T> int countByQuery(String query, Object... args) {
		EntityManager entityManager = getEntityManager();
		Query q = entityManager.createQuery(query);
		for(int i = 0; i < args.length; ++i) {
			q.setParameter(i + 1, args[i]);
		}
		
		return ((Long) q.getSingleResult()).intValue();
	}
	
	@SuppressWarnings("unchecked")
	protected static <T> List<T> findAllBy(Class<T> entityClass, String filter, Object... args) {
		Query query = buildQuery(entityClass, "SELECT obj", filter, args);
		return (List<T>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	protected static <T> List<T> findAllBy(Class<T> entityClass, int offset, int limit, String filter, Object... args) {
		Query query = buildQuery(entityClass, "SELECT obj", filter, args);
		query.setFirstResult(offset);
		
		if(limit != -1)
			query.setMaxResults(limit);
		
		return (List<T>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	protected static <T> T findBy(Class<T> entityClass, String filter, Object... args) {
		Query query = buildQuery(entityClass, "SELECT obj", filter, args);
		query.setMaxResults(1);
		
		return (T) query.getSingleResult();
	}
	
	public abstract int getVersion();

	public abstract K getId();
	
	
	public void persist() {
		EntityManager entityManager = getEntityManager();
		entityManager.persist(this);
		
	}
	
	public void remove() {
		EntityManager entityManager = getEntityManager();
		entityManager.remove(this);
	}
	
}
