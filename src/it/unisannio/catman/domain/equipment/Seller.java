package it.unisannio.catman.domain.equipment;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Seller extends Supplier<Offer, Seller> {
	
	public static Seller findSeller(Long id) {
		return find(Seller.class, id);
	}

	@OneToMany(mappedBy="supplier")
	private List<Offer> supply = new ArrayList<Offer>(); 
	
	
	public Seller findSeller(long id) {
		return find(Seller.class, id);
	}

	public List<Offer> getSupply() {
		return supply;
	}

	@Override
	public void addSupply(Offer offer) {
		supply.add(offer);
	}
	
	@Override
	public void persist() {
		super.persist();
	}


}
