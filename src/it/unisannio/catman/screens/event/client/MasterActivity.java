package it.unisannio.catman.screens.event.client;

import it.unisannio.catman.common.client.LoadingScreenActivity;
import it.unisannio.catman.common.client.ScreenActivity;
import it.unisannio.catman.domain.workflow.client.EventProxy;
import it.unisannio.catman.domain.workflow.client.EventRequest;
import it.unisannio.catman.screens.event.client.Event.Presenter;
import it.unisannio.catman.screens.event.client.Event.View;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class MasterActivity extends LoadingScreenActivity<EventRequest, EventProxy, Event.View> {
	
	public MasterActivity() {
		super(getDataStore().events());
	}
	
	@Override
	protected View onViewSetup() {
		return new MasterView();
	}

	@Override
	protected void onLoad(EventProxy object) {
		View view = getView();
		Presenter p = new EventPresenter(object, this);
		view.setPresenter(p);
		p.setView(view);
	}

}
