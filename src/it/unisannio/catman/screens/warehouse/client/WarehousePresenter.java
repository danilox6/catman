package it.unisannio.catman.screens.warehouse.client;

import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.ErrorHandler;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.ScreenActivity;
import it.unisannio.catman.domain.equipment.client.SupplyProxy;
import it.unisannio.catman.domain.equipment.client.WarehouseProxy;
import it.unisannio.catman.screens.warehouse.client.queries.StockQuery;

public class WarehousePresenter implements Warehouse.Presenter{
	
	private Warehouse.View view;
	private ScreenActivity screenActivity;
	
	public WarehousePresenter(Warehouse.View v, ScreenActivity activity, Intent intent) {
		this.view = v;
		this.screenActivity = activity;
		
		DataStore dataStore = App.getInstance().getDataStore();
		try{
			EntityProxyId<WarehouseProxy> entityId = dataStore.getProxyId(intent.get(0, ""));
			dataStore.warehouses().find(entityId).fire(new Receiver<WarehouseProxy>() {

				@Override
				public void onSuccess(final WarehouseProxy warehouse) {
					view.setWarehouseProxy(warehouse);
					view.setStockProxyQuery(new StockQuery(warehouse));
				}

				@Override
				public void onFailure(ServerFailure error) {
					ErrorHandler.handle(error.getMessage()); 
				}
			});
		}catch(IllegalArgumentException e){
			ErrorHandler.handle(); 
		}
	
		
	}

	@Override
	public void goToSupplyScreen(SupplyProxy m) {
		String id = App.getInstance().getDataStore().getHistoryToken(m.stableId());
		screenActivity.goTo(new Intent("supply").withParams(id));
	}

}
