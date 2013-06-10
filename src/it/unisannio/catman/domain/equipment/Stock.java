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
	
}
