package it.unisannio.catman.screens.workers.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import it.unisannio.catman.common.client.ScreenActivity;

public class DetailActivity extends ScreenActivity implements Workers.Detail {

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(new DetailView());
	}

}
