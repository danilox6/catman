package it.unisannio.catman.screens.materialmanager.client;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.ScreenActivity;
import it.unisannio.catman.domain.equipment.client.SupplierProxy;
import it.unisannio.catman.domain.equipment.client.WarehouseProxy;
import it.unisannio.catman.screens.materialmanager.client.queries.SupplierQuery;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

@SuppressWarnings("rawtypes") 
public class MasterActivity extends ScreenActivity implements MaterialManager.Presenter {

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		MaterialManager.View view = new MasterView();
		panel.setWidget(view);
		
		view.setPresenter(this);
		view.setSupplierQuery(new SupplierQuery());
		
	}

	@Override
	public void goToSupplyScreen(SupplierProxy s) {
		DataStore ds = App.getInstance().getDataStore();
		goTo(new Intent(s instanceof WarehouseProxy?"warehouse":"seller")
				.withParams(ds.getHistoryToken(s.stableId())));

	}

}
