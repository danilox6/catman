package it.unisannio.catman.screens.materialmanager.client;

import java.util.List;


import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.common.client.ScreenActivity;
import it.unisannio.catman.domain.equipment.client.SupplierProxy;
import it.unisannio.catman.domain.equipment.client.WarehouseProxy;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.Request;

@SuppressWarnings("rawtypes") 
public class MasterActivity extends ScreenActivity implements MaterialManager.Master {

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		final MaterialManager.Master.View masterView = new MasterView(this);

		final DataStore dataStore = App.getInstance().getDataStore();
		masterView.setSupplierQuery(new Query<SupplierProxy>(){

			@Override
			public Request<List<SupplierProxy>> list(int start, int length) {
				return dataStore.suppliers().listAll(start, length);
			}

			@Override
			public Request<Integer> count() {
				return dataStore.suppliers().count();
			}

			@Override
			public Request<Void> deleteAll(List<SupplierProxy> skip) {
				throw new UnsupportedOperationException();
			}

			@Override
			public Request<Void> deleteSet(List<SupplierProxy> set) {
				throw new UnsupportedOperationException();
			}

		});

		panel.setWidget(masterView);
	}

	@Override
	public void goToSupplyScreen(SupplierProxy s) {
		DataStore ds = App.getInstance().getDataStore();
		goTo(new Intent(s instanceof WarehouseProxy?"warehouse":"seller")
				.withParams(ds.getHistoryToken(s.stableId())));

	}

}
