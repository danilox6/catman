package it.unisannio.catman.domain.equipment;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Warehouse extends Supplier<Stock, Warehouse> {
	public static Warehouse findWarehouse(Long id) {
		return find(Warehouse.class, id);
	}

	@OneToMany(mappedBy="supplier")
	private List<Stock> supply = new ArrayList<Stock>(); 

	@Override
	public List<Stock> getSupply() {
		return supply;
	}

	@Override
	public void persist() {
		super.persist();
	}
	
	public void addSupply(Stock stock){
		supply.add(stock);
	}
	
}
