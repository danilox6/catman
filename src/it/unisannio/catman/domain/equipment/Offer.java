package it.unisannio.catman.domain.equipment;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
@Entity
public class Offer extends Supply<Offer, Seller> {
	
	public static Offer findOffer(SupplyKey id) {
		return find(Offer.class, id);
	}
	
	public static List<Offer> listBySeller(Seller seller, int start, int length){
		return listByQuery(Offer.class, start, length, "SELECT o FROM Offer o WHERE o.supplier = ?1", seller);
	}
	
	public static int countBySeller(Seller seller) {
		return countByQuery("SELECT COUNT(o) FROM Offer o WHERE o.supplier = ?1", seller);
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
		supplier.addSupply(this);
		this.supplier = supplier;
		getId().supplierId = supplier.getId();
	}
	
	

}
