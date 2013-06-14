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
	

	public static List<Offer> listBySeller(Seller seller, String searchQuery, int start, int length){
		return listByQuery(Offer.class, start, length, "SELECT o FROM Offer o WHERE o.supplier = ?1"
				+ (searchQuery != null && !searchQuery.trim().equals("")?" AND lower(o.materiel.name) LIKE '%"+searchQuery.toLowerCase()+"%'":""), seller);
	}
	
	public static int countBySeller(Seller seller, String searchQuery) {
		return countByQuery("SELECT COUNT(o) FROM Offer o WHERE o.supplier = ?1"
				+ (searchQuery != null && !searchQuery.trim().equals("")?" AND lower(o.materiel.name) LIKE '%"+searchQuery.toLowerCase()+"%'":""), seller);
	}

	public static List<Offer> findByMateriel(Materiel m){
		return findByQuery("SELECT s FROM Offer s WHERE s.materiel = ?1"
				, m);
	}
	
	public static int countByMateriel(Materiel m){
		return countByQuery("SELECT COUNT(s) FROM Offer s WHERE s.materiel = ?1", m);
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
	
	
	public int buy(int quantity, Warehouse destination) {
		if(quantity < 1)
			throw new IllegalArgumentException("At least one unit must be moved. " + quantity + " given.");
		
		if(quantity > getQuantity())
			throw new IllegalArgumentException("Cannot move more than " + quantity + " units. " + getQuantity() + " available");
		
		Stock dest = Stock.findOrCreate(destination, getMateriel());
		dest.setQuantity(dest.getQuantity() + quantity);
		setQuantity(getQuantity() - quantity);
		
		return getQuantity();
	}

}
