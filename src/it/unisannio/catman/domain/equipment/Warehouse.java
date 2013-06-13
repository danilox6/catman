package it.unisannio.catman.domain.equipment;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Warehouse extends Supplier<Stock, Warehouse> {
	public static Warehouse findWarehouse(Long id) {
		return find(Warehouse.class, id);
	}

	public static List<Warehouse> findAll() {
		return findAll(Warehouse.class);
	}
	
	@OneToMany(mappedBy="supplier", cascade = CascadeType.ALL)
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
