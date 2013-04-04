package it.unisannio.catman;

import static org.junit.Assert.*;
import it.unisannio.catman.common.server.Database;
import it.unisannio.memento.DAOFactory;
import it.unisannio.memento.AbstractDAO;
import it.unisannio.memento.fixtures.PlainObject;

import org.junit.Test;

public class DatabaseTest {

	@Test
	public void test() {
		Database db = Database.get();
		assertNotNull(db);
		
		DAOFactory factory = db.getDaoFactory();
		assertNotNull(factory);
		
		AbstractDAO<Object, PlainObject> dao = (AbstractDAO<Object, PlainObject>) factory.getDaoFor(PlainObject.class);
		PlainObject obj = new PlainObject(1, "Test");
		dao.insert(obj);
		
		assertNotNull(dao.find(new Integer(1)));
	}

}
