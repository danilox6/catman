package it.unisannio.catman.domain.equipment.client;

import java.util.List;

import it.unisannio.catman.domain.equipment.Stock;
import it.unisannio.catman.domain.equipment.Warehouse;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Warehouse.class)
public interface WarehouseProxy extends SupplierProxy{
	List<StockProxy> getSupply();
	String getName();
	void setName(String name);
}
