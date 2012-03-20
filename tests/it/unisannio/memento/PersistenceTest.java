package it.unisannio.memento;

import static org.junit.Assert.*;
import it.unisannio.memento.fixtures.SampleObject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

/**
 * Semplice test per verificare le impostazioni del persistence.xml e il funzionamento delle librerie.
 */
public class PersistenceTest {

	@Test
	public void test() {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("memento-junit");
			EntityManager em = emf.createEntityManager();
			
			em.persist(new SampleObject());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Wrong DB settings or missing libraries.");
		}
		
	}

}
