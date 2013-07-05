package it.unisannio.catman.domain.equipment.client;

import it.unisannio.catman.domain.equipment.Stock;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Stock.class)
public interface StockProxy extends SupplyProxy {
	void setSupplier(WarehouseProxy warehouse);
	public WarehouseProxy getSupplier();
}
