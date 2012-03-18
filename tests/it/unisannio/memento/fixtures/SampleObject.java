package it.unisannio.memento.fixtures;

import it.unisannio.memento.CustomDAO;
import it.unisannio.memento.GenericDAO;

public class SampleObject implements CustomDAO<SampleObject.DAO>{
	
	public static interface DAO extends GenericDAO<Integer, SampleObject> {
		SampleObject findByAge(int age);
	}
}
