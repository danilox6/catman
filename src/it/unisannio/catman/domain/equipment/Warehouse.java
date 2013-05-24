package it.unisannio.catman.domain.equipment;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Version;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Warehouse extends Supplier<Stock, Warehouse> {
	public static Warehouse findWarehouse(Long id) {
		return find(Warehouse.class, id);
	}
	
	@Id
	@GeneratedValue
	private long id;
	
	@Version
	private int version;


	@Override
	public int getVersion() {
		return version;
	}

	@Override
	public Long getId() {
		return id;
	}

}
