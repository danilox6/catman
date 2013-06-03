package it.unisannio.catman.screens.eventmanager.client;

import java.util.List;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.common.client.ScreenActivity;
import it.unisannio.catman.domain.workflow.client.EventProxy;
import it.unisannio.catman.screens.event.client.Event;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.Request;

public class MasterActivity extends ScreenActivity implements EventManager.Master {
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		
		MasterView masterView = new MasterView();
		
		final DataStore store = App.getInstance().getDataStore();

		Query<EventProxy> query = new Query<EventProxy>() {

			@Override
			public Request<List<EventProxy>> list(int start, int length) {
				return store.events().listAll(start, length);
			}

			@Override
			public Request<Integer> count() {
				return store.events().count();
			}

			@Override
			public Request<Void> deleteAll(List<EventProxy> skip) {
				throw new UnsupportedOperationException(); // FIXME
			}

			@Override
			public Request<Void> deleteSet(List<EventProxy> set) {
				throw new UnsupportedOperationException(); // FIXME
			}
		};
		
		masterView.setEventQuery(query);
		panel.setWidget(masterView);
		
	}

}
