package it.unisannio.memento;

import static org.junit.Assert.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import it.unisannio.memento.fixtures.ExternalDAO;
import it.unisannio.memento.fixtures.ObjectWithDeclaredDAO;
import it.unisannio.memento.fixtures.ObjectWithImplicitDAO;
import it.unisannio.memento.fixtures.PlainObject;
import it.unisannio.memento.jpa.DAOFactoryImpl;

import org.junit.BeforeClass;
import org.junit.Test;

public class DAODetectionTest {
	private static DAOFactory factory;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("memento-junit");
		factory = new DAOFactoryImpl(emf.createEntityManager());
	}
	
	@Test
	public void testObjectWithoutDAO() {
		GenericDAO<?, PlainObject> dao = factory.getGenericDaoFor(PlainObject.class);
		assertNotNull(dao);
	}
	

	@Test
	public void testObjectWithImplicitDAO() {
		GenericDAO<?, ObjectWithImplicitDAO> dao = factory.getDaoFor(ObjectWithImplicitDAO.class);
		assertNotNull(dao);
		assertTrue(dao instanceof ObjectWithImplicitDAO.DAO);
		
		GenericDAO<?, ObjectWithImplicitDAO> genericDao = factory.getGenericDaoFor(ObjectWithImplicitDAO.class);
		assertNotNull(genericDao);
		assertTrue(dao instanceof ObjectWithImplicitDAO.DAO);
	}

	@Test
	public void testObjectWithDeclaredDAO() {
		GenericDAO<?, ObjectWithDeclaredDAO> dao = factory.getDaoFor(ObjectWithDeclaredDAO.class);
		assertNotNull(dao);
		assertTrue(dao instanceof ExternalDAO);
		
		GenericDAO<?, ObjectWithDeclaredDAO> genericDao = factory.getGenericDaoFor(ObjectWithDeclaredDAO.class);
		assertNotNull(genericDao);
		assertTrue(dao instanceof ExternalDAO);
	}

}
