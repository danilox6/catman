package it.unisannio.catman.domain.equipment;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import it.unisannio.catman.domain.contacts.Addressable;

@Entity
public class Supplier<T extends Supply<T,S>, S extends Supplier<T,S>> extends Addressable {
	@Id
	@GeneratedValue
	private long id;
	
	@Version
	private int version;

	private String name;
	
	@OneToMany
	private List<T> supply;
	
	public static Supplier<?,?> findSupplier(Long id) {
		return find(Supplier.class, id);
	}
	
	@SuppressWarnings("rawtypes")
	public static List<Supplier> listAll(int start, int length) {
		return list(Supplier.class, start, length);
	}
	
	public static int count() {
		return count(Supplier.class);
	}
	
	public List<T> getSupply() {
		return supply;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	public int getVersion() {
		return version;
	}

}
