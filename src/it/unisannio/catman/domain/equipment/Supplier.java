package it.unisannio.catman.domain.equipment;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import it.unisannio.catman.domain.contacts.Addressable;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Supplier<T extends Supply<T,S>, S extends Supplier<T,S>> extends Addressable {
	@Id
	@GeneratedValue
	private long id;
	
	@Version
	private int version;

	@NotEmpty(message = "Supplier name can't be empty")
	private String name;
	
	
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
	
	public abstract List<T> getSupply();

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
