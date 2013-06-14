package it.unisannio.catman.screens.eventmanager.client;

import java.util.Date;
import java.util.List;

import it.unisannio.catman.common.client.AbstractQuery;
import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.common.client.ScreenActivity;
import it.unisannio.catman.domain.workflow.client.EventProxy;
import it.unisannio.catman.screens.eventmanager.client.queries.EventManagerQuery;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.Request;

public class MasterActivity extends ScreenActivity implements EventManager.Master {
	
	View view;
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		
		view = new MasterView(this);
		
		view.setEventQuery(new EventManagerQuery(null, null));
		panel.setWidget(view);
		
	}

	@Override
	public void goToEventScreen(EventProxy e) {
		DataStore ds = App.getInstance().getDataStore();
		goTo(new Intent("event").withParams(ds.getHistoryToken(e.stableId())));
	}

	@Override
	public void executeSearch(String searchQuery, Date date) {
		view.setEventQuery(new EventManagerQuery(searchQuery, date));
	}

}
