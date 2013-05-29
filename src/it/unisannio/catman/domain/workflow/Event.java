package it.unisannio.catman.domain.workflow;

import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import it.unisannio.catman.common.server.AbstractEntity;
import it.unisannio.catman.domain.documents.Document;
import it.unisannio.catman.domain.documents.Dossier;

@Entity
public class Event extends AbstractEntity<Long> implements Dossier<EventStatus>{
	
	public static Event findEvent(Long id) {
		return find(Event.class, id);
	}
	
	public static List<Event> findAll() {
		return findAll(Event.class);	
	}
	
	public static List<Event> listAll(int start, int length) {
		return list(Event.class, start, length);
	}
	
	public static void delete(Long... keys) {
		deleteAll(Event.class, keys);
	}
	
	public static int count() {
		return count(Event.class);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Version private int version;
	
	@ManyToOne
	private Customer customer;
	
	private String title;
	
	public Event() { }

	@Override
	public int getVersion() {
		return version;
	}

	@Override
	public Long getId() {
		return id;
	}

	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@Override
	public Iterator<Document> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventStatus getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Document d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Document d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SortedSet<Document> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
}
