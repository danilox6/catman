package it.unisannio.catman.screens.event.client;

import it.unisannio.catman.common.client.LoadingScreenActivity;
import it.unisannio.catman.domain.workflow.client.EventProxy;
import it.unisannio.catman.domain.workflow.client.EventRequest;
import it.unisannio.catman.screens.event.client.Event.Presenter;
import it.unisannio.catman.screens.event.client.Event.View;

public class DetailActivity extends LoadingScreenActivity<EventRequest, EventProxy, Event.View> {

	public DetailActivity() {
		super(getDataStore().events());
	}

	@Override
	protected View onViewSetup() {
		return new DetailView();
	}

	@Override
	protected void onLoad(EventProxy object) {
		View view = getView();
		Presenter p = new EventPresenter(object, this);
		view.setPresenter(p);
		p.setView(view);
	}
}
