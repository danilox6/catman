package it.unisannio.catman.screens.warehouse.client;

import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.google.web.bindery.requestfactory.shared.Receiver;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.ErrorHandler;
import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.common.client.ScreenActivity;
import it.unisannio.catman.domain.equipment.client.StockProxy;
import it.unisannio.catman.domain.equipment.client.WarehouseProxy;

public class DetailActivity extends ScreenActivity implements Warehouse.Detail{

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		final Warehouse.Detail.View detailView = new DetailView();
		panel.setWidget(detailView);

		final DataStore dataStore = App.getInstance().getDataStore();
		try{
			EntityProxyId<WarehouseProxy> entityId = dataStore.getProxyId(getIntent().get(0, ""));
			dataStore.warehouses().find(entityId).fire(new Receiver<WarehouseProxy>() {

				@Override
				public void onSuccess(final WarehouseProxy response) {
					detailView.setWarehouseProxy(response);
					Query<StockProxy> query = new Query<StockProxy>() {
						
						@Override
						public Request<List<StockProxy>> list(int start, int length) {
							return dataStore.stocks().listStocksByWarehouse(response, start, length);
						}
						
						@Override
						public Request<Void> deleteSet(List<StockProxy> set) {
							// TODO Auto-generated method stub
							return null;
						}
						
						@Override
						public Request<Void> deleteAll(List<StockProxy> skip) {
							// TODO Auto-generated method stub
							return null;
						}
						
						@Override
						public Request<Integer> count() {
							// TODO Auto-generated method stub
							return null;
						}
					};
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

}
