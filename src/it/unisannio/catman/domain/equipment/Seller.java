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

	@OneToMany(mappedBy="supplier")
	private List<Offer> supply;
	
	
	public Seller findSeller(long id) {
		return find(Seller.class, id);
	}

	public List<Offer> getSupply() {
		return supply;
	}

}
