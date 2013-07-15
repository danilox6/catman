package it.unisannio.catman.domain.equipment;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Version;
import org.hibernate.validator.constraints.NotEmpty;

import it.unisannio.catman.domain.contacts.Addressable;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Supplier<T extends Supply<T,S>, S extends Supplier<T,S>> extends Addressable {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
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
		return listByQuery(Supplier.class, start, length, "SELECT s FROM Supplier s ");
//		return list(Supplier.class, start, length);
	}
	
	
	//FIXME Query inutili
	@SuppressWarnings("rawtypes")
	public static List<Supplier> listByMateriel(Materiel m, int start, int length){
		return listByQuery(Supplier.class, start, length, "SELECT s FROM Supplier s " +
				"INNER JOIN s.supply supply " +
				"WHERE supply.materiel = ?1", m);
	}
	
	public static int countByMateriel(Materiel m){
		return countByQuery("SELECT COUNT(s) FROM Supplier s " +
				"INNER JOIN s.supply supply " +
				"WHERE supply.materiel = ?1", m);
	}
	
	public static int count() {
		return count(Supplier.class);
	}
	
	public abstract List<T> getSupply();
	public abstract void addSupply(T supply);
	
	public int getSupplyCount(){
		return getSupply().size();
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
