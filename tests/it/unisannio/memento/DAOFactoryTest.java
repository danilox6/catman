package it.unisannio.memento;

import static org.junit.Assert.*;
import it.unisannio.memento.fixtures.SampleObject;

import org.junit.Test;

public class DAOFactoryTest {

	@Test
	public void test() {
		DAOFactory factory = null;
		SampleObject.DAO dao = factory.getDaoFor(SampleObject.class);
		fail("Not yet implemented");
	}

}
