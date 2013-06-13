package it.unisannio.catman.domain.equipment;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Stock extends Supply<Stock, Warehouse> {
	public static Stock findStock(SupplyKey supplyKey) {
		return find(Stock.class, supplyKey);
	}
	
	public static List<Stock> listAll(int start, int length) {
		return list(Stock.class, start, length);
	}
	
	public static Integer count(){
		return count(Stock.class);
	} 

	public static List<Stock> listByWarehouse(Warehouse warehouse, int start, int length){
		return listByQuery(Stock.class, start, length, "SELECT st FROM Stock st WHERE st.supplier = ?1", warehouse);
	}
	
	public static int countByWarehouse(Warehouse warehouse) {
		return countByQuery("SELECT COUNT(st) FROM Stock st WHERE st.supplier = ?1", warehouse);
	}
	
	public static Stock findOrCreate(Warehouse w, Materiel m) {
		List<Stock> stocks = findByQuery("SELECT s FROM Stock s WHERE s.supplier = ?1 AND s.materiel = m", w, m);
		if(stocks.isEmpty()) {
			Stock st = new Stock();
			st.setSupplier(w);
			st.setMateriel(m);
			st.persist();
			
			return st;
		}
		
		return stocks.get(0);
	}
	
	@ManyToOne
	private Warehouse supplier;

	public Warehouse getSupplier() {
		return supplier;
	}
	
	public void setSupplier(Warehouse supplier) {
		this.supplier = supplier;
		
		this.supplier.addSupply(this);
		getId().supplierId = supplier.getId();
	}
	
	public int move(int quantity, Warehouse destination) {
		if(quantity < 1)
			throw new IllegalArgumentException("At least one unit must be moved. " + quantity + " given.");
		
		if(quantity > getQuantity())
			throw new IllegalArgumentException("Cannot move more than " + quantity + " units. " + getQuantity() + " available");
		Stock dest = findOrCreate(destination, getMateriel());
		dest.setQuantity(dest.getQuantity() + quantity);
		setQuantity(getQuantity() - quantity);
		
		return getQuantity();
	}
	
}
