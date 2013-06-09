package it.unisannio.catman.domain.equipment;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
@Entity
public class Offer extends Supply<Offer, Seller> {
	
	public static Offer findOffer(SupplyKey id) {
		return find(Offer.class, id);
		
	}

	private float price;
	
	@ManyToOne
	private Seller supplier;

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public Seller getSupplier() {
		return supplier;
	}

	public void setSupplier(Seller supplier) {
		this.supplier = supplier;
		getId().supplierId = supplier.getId();
	}
	
	

}
