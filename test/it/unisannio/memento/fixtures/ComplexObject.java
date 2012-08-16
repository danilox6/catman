package it.unisannio.memento.fixtures;

import java.util.List;

import javax.persistence.*;

import it.unisannio.memento.CustomDAO;
import it.unisannio.memento.Delegate;
import it.unisannio.memento.GenericDAO;
import it.unisannio.memento.jpa.Statement;

@Entity
public class ComplexObject implements CustomDAO<ComplexObject.DAO>{
	public static interface DAO extends GenericDAO<Integer, ComplexObject> {
		public List<ComplexObject> listByName(int off, int count, String name);
		public List<ComplexObject> listByNameAndCost(int off, int count, String name, double cost);
		
		public List<ComplexObject> findAllByName(String name);
		public List<ComplexObject> findAllByNameAndCost(String name, double cost);
		
		public ComplexObject findByName(String name);
		public ComplexObject findByNameAndCost(String name, double cost);
		
		long countByName(String name);
		long countByNameAndCost(String name, double cost);
		
		void unsupportedMethod();
		
		@Statement("SELECT obj FROM ComplexObject obj WHERE cost > ?")
		List<ComplexObject> findByCostGreaterThan(double cost);
		
		@Delegate(ComplexObject.class)
		int answerToTheFundamentalQuestion();
	}
	
	public static int answerToTheFundamentalQuestion() {
		return 42;
	}
	
	/**
	 * @uml.property  name="id"
	 */
	@Id
	private int id;
	
	/**
	 * @uml.property  name="name"
	 */
	private String name;
	/**
	 * @uml.property  name="cost"
	 */
	private double cost;
	
	@SuppressWarnings("unused")
	private ComplexObject() {}
	
	public ComplexObject(int id, String name, double cost) {
		this.id = id;
		this.name = name;
		this.cost = cost;
	}

	/**
	 * @return
	 * @uml.property  name="id"
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 * @uml.property  name="id"
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 * @uml.property  name="name"
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 * @uml.property  name="cost"
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * @param cost
	 * @uml.property  name="cost"
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	
}
