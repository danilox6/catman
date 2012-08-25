package it.unisannio.memento.fixtures;

import javax.persistence.Entity;
import javax.persistence.Id;

import it.unisannio.memento.AbstractDAO;

@Entity
public class ObjectWithImplicitDAO {
	public static interface DAO extends AbstractDAO<Integer, ObjectWithImplicitDAO> {}
	
	@SuppressWarnings("unused")
	@Id
	private int id;
}
