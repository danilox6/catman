package it.unisannio.memento.jpa;

import it.unisannio.memento.CustomDAO;
import it.unisannio.memento.DAOFactory;
import it.unisannio.memento.GenericDAO;
import it.unisannio.memento.PersistedBy;
import it.unisannio.memento.Persists;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

public class DAOFactoryImpl implements DAOFactory {
	
	private class GenericDAOImpl<K, T> implements GenericDAO<K, T> {
		private final Class<T> entityClass;
		private final String id;
		
		GenericDAOImpl(Class<T> objClass) {
			this.entityClass = objClass;
			
			EntityType<T> entityType = entityManager.getMetamodel().entity(entityClass);
			this.id = entityType.getId(entityType.getIdType().getJavaType()).getName();
		}

		@Override
		public T find(K key) {
			return entityManager.find(entityClass, key);
		}

		@Override
		public List<T> findAll(K... keys) {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<T> cq = cb.createQuery(entityClass);
			Root<T> root = cq.from(entityClass);
			if(keys.length > 0) {
				In<Object> in = cb.in(root.get(id));
				for(K key : keys)
					in.value(key);
				cq.where(in);
			}
			return entityManager.createQuery(cq).getResultList();
			
		}

		@Override
		public long count() {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> cq = cb.createQuery(Long.class);
			cq.select(cb.count(cq.from(entityClass)));
			return entityManager.createQuery(cq).getSingleResult();
		}

		@Override
		public void insert(T obj) {
			entityManager.persist(obj);
		}

		@Override
		public List<T> list(int offset, int size) {
			CriteriaQuery<T> cq = entityManager.getCriteriaBuilder().createQuery(entityClass);
			cq.from(entityClass);
			return entityManager.createQuery(cq)
					.setFirstResult(offset)
					.setMaxResults(size)
					.getResultList();
		}

		@Override
		public void deleteAll(List<T> objects) {
			for(T obj : objects) entityManager.remove(obj);
		}

		@Override
		public void deleteAll(K... keys) {
			List<K> ids = Arrays.asList(keys);
			TypedQuery<T> select = entityManager.createQuery("SELECT o from " + entityClass.getName() + " o WHERE " + id +" IN (:in)", entityClass);
			select.setParameter("in", ids);
			for(T obj : select.getResultList())
				entityManager.detach(obj);
			Query delete = entityManager.createQuery("DELETE from " + entityClass.getName() + " WHERE " + id + " IN (:in)");
			delete.setParameter("in", ids).executeUpdate();
			entityManager.flush();
		}

		@Override
		public void delete(T obj) {
			entityManager.remove(obj);
		}
	}

	private final Map<Class<?>, WeakReference<GenericDAO<?, ?>>> cache;
	private final EntityManager entityManager;
	
	public DAOFactoryImpl(EntityManager em) {
		this.cache = new HashMap<Class<?>, WeakReference<GenericDAO<?, ?>>>();
		this.entityManager = em;
	}
	
	@SuppressWarnings("unchecked")
	private <D extends GenericDAO<K, T>, K, T> D getInstance(Class<D> daoClass) {
		D instance = cache.containsKey(daoClass) ? (D) cache.get(daoClass).get() : null;
		
		if(instance == null) {
			if(daoClass.isInterface()) {
				final Class<T> entityClass;
			
				Persists annotation = daoClass.getAnnotation(Persists.class);
				if(annotation != null)
					entityClass = (Class<T>) annotation.value();
				else if(daoClass.isMemberClass())
					entityClass = (Class<T>) daoClass.getDeclaringClass();
				else 
					throw new IllegalStateException("DAO interface must bring a @Persists annotation or be declared inside the class it persists");
				
				instance = (D) Proxy.newProxyInstance(daoClass.getClassLoader(), new Class<?>[] { daoClass }, new InvocationHandler() {
					
					GenericDAOImpl<K, T> delegate = new GenericDAOImpl<K, T>(entityClass);
					@Override
					public Object invoke(Object obj, Method method, Object[] args)
							throws Throwable {
						
						JPQL query = method.getAnnotation(JPQL.class);
						if(query != null) { // method is annotated with the custom query to execute
							Class<?> returnType = method.getReturnType();
							boolean multiple = returnType.isArray() || returnType.equals(List.class);
							Query q = entityManager.createQuery(query.value());
							
							for(int i = 0; i < args.length; ++i) {
								q.setParameter(i, args[i]);
							}
							
							return multiple ? q.getResultList() : q.getSingleResult();
						}
						
						String methodName = method.getName();
						if(methodName.contains("By")) { // dynamic method
							int byPosition = methodName.indexOf("By");
							String verb = methodName.substring(0, byPosition);
							String complement = methodName.substring(byPosition + 2, methodName.length());
							String[] filters = complement.split("And");
							
							int argsStart = "list".equals(verb) ? 2 : 0;
							if(filters.length != args.length - argsStart)
								throw new IllegalArgumentException("Arguments mismatch for method " 
										+ method + ": " + filters.length + " filters for " + (args.length - argsStart) + " arguments");
							
							CriteriaBuilder cb = entityManager.getCriteriaBuilder();
							CriteriaQuery<Object> cq = cb.createQuery();
							
							Predicate[] predicates = new Predicate[filters.length];
							Root<T> root = cq.from(entityClass);
							for(int i = 0; i < filters.length; ++i) {
								predicates[i] = cb.equal(root.get(filters[i]), args[argsStart + i]);
							}
							cq.where(predicates);
							if("find".equals(verb)) {
								return entityManager.createQuery(cq)
									.setMaxResults(1)
									.getSingleResult();
							} else if("findAll".equals(verb)) {
								return entityManager.createQuery(cq)
										.getResultList();
							} else if("list".equals(verb)) {
								return entityManager.createQuery(cq)
									.setFirstResult((Integer) args[0])
									.setMaxResults((Integer) args[1])
									.getResultList();
							} else if("count".equals(verb)) {
								cq.select(cb.count(root));
								return entityManager.createQuery(cq).getSingleResult();
							} else {
								throw new UnsupportedOperationException("'" + verb + "' is not a valid verb for auto-generation of " + method);
							}
						}
						
						// it should be one of the "usual" methods -> delegate it
						try {
							Method delegated = delegate.getClass().getMethod(method.getName(), method.getParameterTypes());
							return delegated.invoke(delegate, args);
						} catch (NoSuchMethodException nsmEx) {
							throw new UnsupportedOperationException("Unable to find a strategy to auto-generate method " + method);
						} catch (InvocationTargetException itEx) {
							throw itEx.getCause();
						}
					}
				});
			} else {
				try {
					instance = daoClass.newInstance();
				} catch (Exception e) {
					throw new RuntimeException("Cannot create DAO instance for class " + daoClass, e);
				}
			}
			
			cache.put(daoClass, new WeakReference<GenericDAO<?, ?>>(instance));
		}
		
		return instance;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <D extends GenericDAO<K, T>, K, T> D getDaoFor(Class<? extends CustomDAO<D>> entityClass) {
		try {
			return (D) getGenericDaoFor(entityClass);
		} catch (ClassCastException ex) {
			throw new IllegalStateException(entityClass + " doesn't delegate persistence to the declared DAO", ex);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <T> GenericDAO<?, T> getGenericDaoFor(Class<T> entityClass) {
		GenericDAO instance = cache.containsKey(entityClass) ? cache.get(entityClass).get() : null;
		
		if(instance == null) {
			PersistedBy annotation = entityClass.getAnnotation(PersistedBy.class);
			if(annotation != null) {
				instance = getInstance(annotation.value());
			} else {
				try {
					Class<GenericDAO> implicitDao = (Class<GenericDAO>) Class.forName(entityClass.getName() + "$DAO");
					instance = getInstance(implicitDao);
				} catch (Exception e) {}
			}
			
			if(instance == null) {
				instance = new GenericDAOImpl(entityClass);
			}
			cache.put(entityClass, new WeakReference(instance));
		}
		
		return instance;
	}

}
