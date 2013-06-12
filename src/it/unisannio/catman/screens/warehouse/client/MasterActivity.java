package it.unisannio.catman.screens.warehouse.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import it.unisannio.catman.common.client.ScreenActivity;

public class MasterActivity extends ScreenActivity {

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		Warehouse.View view = new MasterView();
		panel.setWidget(view);
		
		WarehousePresenter presenter = new WarehousePresenter(view, this, getIntent());
		view.setPresenter(presenter);
	}
}
