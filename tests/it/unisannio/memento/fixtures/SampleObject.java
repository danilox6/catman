package it.unisannio.memento.fixtures;

import javax.persistence.Entity;
import javax.persistence.Id;

import it.unisannio.memento.CustomDAO;
import it.unisannio.memento.GenericDAO;

@Entity
public class SampleObject implements CustomDAO<SampleObject.DAO>{
	
	public static interface DAO extends GenericDAO<Integer, SampleObject> {
		SampleObject findByAge(int age);
	}
	
	@Id
	private long id;
	private String name;
}
