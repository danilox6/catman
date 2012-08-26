package it.unisannio.catman;

import it.unisannio.memento.DAOFactory;
import it.unisannio.memento.gwt.LocatorDAOFactory;

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
	
	private final EntityManager entityManager;
	private final DAOFactory daoFactory;
	
	private Database() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Setup.DATABASE_PERSISTENCE_UNIT);
		entityManager = emf.createEntityManager();
		daoFactory = new LocatorDAOFactory(entityManager);
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public DAOFactory getDaoFactory() {
		return daoFactory;
	}

}
