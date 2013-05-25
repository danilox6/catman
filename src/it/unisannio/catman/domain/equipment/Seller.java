package it.unisannio.catman.domain.equipment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Version;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Seller extends Supplier<Offer, Seller> {
	
	public static Seller findSeller(Long id) {
		return find(Seller.class, id);
	}
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Version 
	private int version;
	
	
	public Seller findSeller(long id) {
		return find(Seller.class, id);
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
