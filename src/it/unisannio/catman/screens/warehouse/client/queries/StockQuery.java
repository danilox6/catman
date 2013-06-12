package it.unisannio.catman.screens.warehouse.client.queries;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.domain.equipment.client.StockProxy;
import it.unisannio.catman.domain.equipment.client.WarehouseProxy;

public class StockQuery implements Query<StockProxy>{
	
	private static final DataStore dataStore = App.getInstance().getDataStore();
	
	private WarehouseProxy warehouse;
	
	public StockQuery(WarehouseProxy warehouseProxy) {
		 this.warehouse = warehouseProxy;
	}

	@Override
	public Request<List<StockProxy>> list(int start, int length) {
		return dataStore.stocks().listByWarehouse(warehouse, start, length).with("materiel.name","materiel.description");
	}
	
	@Override
	public Request<Integer> count() {
		return dataStore.stocks().countByWarehouse(warehouse);
	}
	
	@Override
	public Request<Void> deleteSet(List<StockProxy> set) {
		throw new UnsupportedOperationException(); //FIXME
	}
	
	@Override
	public Request<Void> deleteAll(List<StockProxy> skip) {
		throw new UnsupportedOperationException(); //FIXME
	}
	
}