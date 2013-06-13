package it.unisannio.catman.screens.eventmanager.client;

import java.util.List;

import it.unisannio.catman.common.client.AbstractQuery;
import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.common.client.ScreenActivity;
import it.unisannio.catman.domain.workflow.client.EventProxy;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.Request;

public class MasterActivity extends ScreenActivity implements EventManager.Master {
	
	View view;
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		
		view = new MasterView(this);
		
		final DataStore store = App.getInstance().getDataStore();

		Query<EventProxy> query = new AbstractQuery<EventProxy>() {

			@Override
			public Request<List<EventProxy>> list(int start, int length) {
				return store.events().listAll(start, length);
			}

			@Override
			public Request<Integer> count() {
				return store.events().count();
			}
		};
		
		view.setEventQuery(query);
		panel.setWidget(view);
		
	}

	@Override
	public void goToEventScreen(EventProxy e) {
		DataStore ds = App.getInstance().getDataStore();
		goTo(new Intent("event").withParams(ds.getHistoryToken(e.stableId())));
	}

}
