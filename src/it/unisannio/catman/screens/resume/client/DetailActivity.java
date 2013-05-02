package it.unisannio.catman.screens.resume.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import it.unisannio.catman.common.client.ScreenActivity;

public class DetailActivity extends ScreenActivity implements Resume.Detail{

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(new DetailView());
	}

}
