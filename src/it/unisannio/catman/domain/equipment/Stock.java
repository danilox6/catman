package it.unisannio.catman.domain.equipment;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Stock extends Supply<Stock, Warehouse> {
	public static Stock findStock(Supply.Key key) {
		return find(Stock.class, key);
	}
	
	@ManyToOne
	private Warehouse supplier;
	
	public Warehouse getSupplier() {
		return supplier;
	}

	public void setSupplier(Warehouse supplier) {
		this.supplier = supplier;
		supplierId = supplier.getId();
	}
}
