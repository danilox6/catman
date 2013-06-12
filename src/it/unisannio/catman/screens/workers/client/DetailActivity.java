package it.unisannio.catman.screens.workers.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import it.unisannio.catman.common.client.ScreenActivity;

public class DetailActivity extends ScreenActivity {

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		Workers.View view = new DetailView();
		panel.setWidget(view);
		
		WorkersPresenter presenter = new WorkersPresenter(view, this, getIntent());
		view.setPresenter(presenter);
	}

}
