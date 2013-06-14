package it.unisannio.catman.domain.workflow;

import it.unisannio.catman.domain.contacts.Contactable;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Version private int version;
	
	@OneToMany(mappedBy = "customer")
	private List<Event> events;
	
	public Customer() {}
	
	
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
	
	public static Integer count() {
		return count(Customer.class);
	}
	
	public static List<Customer> listAll(int start, int length) {
		return list(Customer.class, start, length);
	}
}
