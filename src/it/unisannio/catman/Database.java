package it.unisannio.catman;

import it.unisannio.memento.CustomDAO;
import it.unisannio.memento.DAOFactory;
import it.unisannio.memento.GenericDAO;
import it.unisannio.memento.jpa.DAOFactoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Database {
	private static Database instance = null;
	
	public static Database get() {
		if(instance == null) {
			instance = new Database();
		}
		
		return instance;
	}
	
	public static <D extends GenericDAO<K, T>, K, T> D daoFor(Class<? extends CustomDAO<D>> entityClass) {
		return get().getDaoFactory().getDaoFor(entityClass);
	}
	
	public static <T> GenericDAO<?, T> genericDaoFor(Class<T> entityClass) {
		return get().getDaoFactory().getGenericDaoFor(entityClass);
	}
	
	private final EntityManager entityManager;
	private final DAOFactory daoFactory;
	
	private Database() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Setup.DATABASE_PERSISTENCE_UNIT);
		entityManager = emf.createEntityManager();
		daoFactory = new DAOFactoryImpl(entityManager);
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public DAOFactory getDaoFactory() {
		return daoFactory;
	}

}
