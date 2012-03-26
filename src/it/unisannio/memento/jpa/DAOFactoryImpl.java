package it.unisannio.memento.jpa;

import it.unisannio.memento.CustomDAO;
import it.unisannio.memento.DAOFactory;
import it.unisannio.memento.Delegate;
import it.unisannio.memento.GenericDAO;
import it.unisannio.memento.PersistedBy;

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
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
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
	private <T> GenericDAO<?, T> getInstance(final Class<T> entityClass, final Class<? extends GenericDAO<?, T>> daoClass) {
		GenericDAO<?, T> instance = cache.containsKey(daoClass) ? (GenericDAO<?, T>) cache.get(daoClass).get() : null;
		
		if(instance == null) {
			if(daoClass.isInterface()) {
				instance = (GenericDAO<?, T>) Proxy.newProxyInstance(daoClass.getClassLoader(), new Class<?>[] { daoClass }, new InvocationHandler() {
					
					GenericDAOImpl<?, T> genericDao = new GenericDAOImpl<Object, T>(entityClass);
					@Override
					public Object invoke(Object obj, Method method, Object[] args)
							throws Throwable {
						String methodName = method.getName();
						
						Delegate delegate = method.getAnnotation(Delegate.class);
						if(delegate != null) {
							try {
								Class<?> delegateTo = delegate.value();
								String delegateMethod = delegate.method();
								
								if(delegateMethod.isEmpty()) 
									delegateMethod = method.getName();
								
								return delegateTo
										.getMethod(methodName, method.getParameterTypes())
										.invoke(delegateTo, args);
							} catch(InvocationTargetException itEx) {
								throw itEx.getCause();
							} catch (Exception ex) {
								throw ex;
							}
						}
						
						int argsOffset = 0, resultsOffset = 0, resultsLimit = -1;
						
						Class<?> returnType = method.getReturnType();		
						boolean noReturn = returnType.equals(Void.TYPE);
						boolean multiple = returnType.equals(List.class);
						
						Statement statement = method.getAnnotation(Statement.class);
						String jpql = null;
						
						if(statement != null) { // method is annotated with the custom query to execute
							jpql = statement.value();
						} else if(method.getName().contains("By")) { // dynamic method
							int byPosition = methodName.indexOf("By");
							
							String verb = methodName.substring(0, byPosition);
							String[] filters = methodName.substring(byPosition + 2).split("And");
							
							if("list".equals(verb)) {
								argsOffset = 2;
								resultsOffset = (Integer) args[0];
								resultsLimit = (Integer) args[1];
								verb = "findAll";
							}
							
							if("find".equals(verb)) {
								resultsLimit = 1;
							}
							
							if(filters.length != (args.length - argsOffset))
								throw new IllegalArgumentException("Arguments mismatch for method " 
										+ method + ": " + filters.length + " filters for " + (args.length - argsOffset) + " arguments");
							
							StringBuffer jpqlBuf = null;
							
							if("findAll".equals(verb) || "find".equals(verb)) {
								jpqlBuf = new StringBuffer("SELECT obj ");
							} else if("count".equals(verb)) {
								jpqlBuf = new StringBuffer("SELECT COUNT(*) ");
							} else {
								throw new UnsupportedOperationException("'" + verb + "' is not a valid verb for auto-generation of " + method);
							}
							
							jpqlBuf.append("FROM ").append(entityClass.getName()).append(" obj");
							
							if(filters.length > 0) {
								jpqlBuf.append(" WHERE ");
								for(int i = 0; i < filters.length; ++i) {
									if(i != 0)
										jpqlBuf.append(" AND ");
									jpqlBuf.append(Character.toLowerCase(filters[i].charAt(0)));
									jpqlBuf.append(filters[i].substring(1));
									jpqlBuf.append(" = ?");
								}
							}
							
							jpql = jpqlBuf.toString();
						}
						
						if(jpql != null) {
							Query query = entityManager.createQuery(jpql);
							for(int i = argsOffset; i < args.length; ++i) {
								query.setParameter(i - argsOffset + 1, args[i]);
							}
							
							if(resultsOffset > 0)
								query.setFirstResult(resultsOffset);
							
							if(resultsLimit != -1)
								query.setMaxResults(resultsLimit);
							
							if(noReturn) {
								query.executeUpdate();
								return null;
							} else {
								return multiple ? query.getResultList() : query.getSingleResult();
							}
						}
						
						// it should be one of the "usual" methods -> delegate it
						try {
							Method delegated = GenericDAOImpl.class.getMethod(method.getName(), method.getParameterTypes());
							return delegated.invoke(genericDao, args);
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
				instance = getInstance(entityClass, (Class<? extends GenericDAO<?, T>>) annotation.value());
			} else {
				try {
					Class<? extends GenericDAO<?, T>> implicitDao = (Class<? extends GenericDAO<?, T>>) Class.forName(entityClass.getName() + "$DAO");
					instance = getInstance(entityClass, implicitDao);
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
