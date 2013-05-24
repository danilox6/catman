package it.unisannio.catman.domain.equipment;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Offer extends Supply<Offer, Seller> {
	
	public static Offer findOffer(Supply.Key id) {
		return find(Offer.class, id);
		
	}

	private float price;

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
