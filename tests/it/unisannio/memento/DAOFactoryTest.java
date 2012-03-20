package it.unisannio.memento;

import static org.junit.Assert.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import it.unisannio.memento.fixtures.SampleObject;
import it.unisannio.memento.jpa.DAOFactoryImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DAOFactoryTest {
	private DAOFactory factory;
	
	@Before
	public void setUp() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("memento-junit");
		factory = new DAOFactoryImpl(emf.createEntityManager());
	}
	
	@Test
	public void test() {
		SampleObject.DAO dao = factory.getDaoFor(SampleObject.class);
		fail("Not yet implemented");
	}
	
	@After
	public void tearDown() {
		
	}

}
