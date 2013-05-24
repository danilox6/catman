package it.unisannio.catman.domain.workflow;

import it.unisannio.catman.domain.contacts.Contactable;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;


@Entity
public class Customer extends Contactable {
	public static Customer findByName(String name) {
		return findBy(Customer.class, "name = ?1", name);
	}
	
	public static Customer findCustomer(Long id) {
		return find(Customer.class, id);
	}
	
	private String name;
	
	@Id private long id;
	@Version private int version;
	
	@OneToMany(mappedBy = "customer")
	private List<Event> events;
	
	public Customer() {}
	
	public Customer(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Event> getEvents() {
		return events;
	}
	
	@Override
	public int getVersion() {
		return version;
	}
	
	@Override
	public Long getId() {
		return id;
	}
}
