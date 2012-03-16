package it.unisannio.memento.jpa;

import it.unisannio.memento.DAOFactory;
import it.unisannio.memento.GenericDAO;
import it.unisannio.memento.Persists;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DAOFactoryImpl implements DAOFactory {

	private final Map<Class<?>, WeakReference<GenericDAO<?, ?>>> instances;
	private final EntityManager entityManager;
	
	public DAOFactoryImpl(EntityManager em) {
		this.instances = new HashMap<Class<?>, WeakReference<GenericDAO<?, ?>>>();
		this.entityManager = em;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <D extends GenericDAO<K, T>, K, T> D create(final Class<D> daoClass) {
		D instance = instances.containsKey(daoClass) ? (D) instances.get(daoClass).get() : null;
		
		if(instance == null) {
			if(daoClass.isInterface()) {
				final Class<T> persistedClass = (Class<T>) daoClass.getAnnotation(Persists.class).value();
				instance = (D) Proxy.newProxyInstance(daoClass.getClassLoader(), new Class<?>[] { daoClass }, new InvocationHandler() {
					
					GenericDAOImpl<K, T> delegate = new GenericDAOImpl<K, T>(entityManager, persistedClass);
					@Override
					public Object invoke(Object obj, Method method, Object[] args)
							throws Throwable {
						
						JPQL query = method.getAnnotation(JPQL.class);
						if(query != null) { // method is annotated with the custom query to execute
							// detect if it should return a result
							// exec query
						}
						
						String methodName = method.getName();
						if(methodName.contains("By")) { // dynamic method
							int byPosition = methodName.indexOf("By");
							String verb = methodName.substring(0, byPosition);
							String complement = methodName.substring(byPosition + 2, methodName.length());
							String[] filters = complement.split("And");
							
							// build query
							// detect if it should return a result
							// exec a query
						}
						
						// it should be one of the "usual" methods -> delegate it
						return null;
					}
				});
			} else {
				try {
					instance = daoClass.newInstance();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			instances.put(daoClass, new WeakReference<GenericDAO<?, ?>>(instance));
		}
		
		return instance;
	}

}
