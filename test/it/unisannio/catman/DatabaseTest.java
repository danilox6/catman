package it.unisannio.catman;

import static org.junit.Assert.*;
import it.unisannio.memento.DAOFactory;
import it.unisannio.memento.GenericDAO;
import it.unisannio.memento.fixtures.PlainObject;

import org.junit.Test;

public class DatabaseTest {

	@Test
	public void test() {
		Database db = Database.get();
		assertNotNull(db);
		
		DAOFactory factory = db.getDaoFactory();
		assertNotNull(factory);
		
		GenericDAO<Object, PlainObject> dao = (GenericDAO<Object, PlainObject>) factory.getGenericDaoFor(PlainObject.class);
		PlainObject obj = new PlainObject(1, "Test");
		dao.insert(obj);
		
		assertNotNull(dao.find(new Integer(1)));
	}

}
