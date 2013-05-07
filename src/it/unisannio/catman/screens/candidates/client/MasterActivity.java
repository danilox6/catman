package it.unisannio.catman.screens.candidates.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import it.unisannio.catman.common.client.ScreenActivity;

public class MasterActivity extends ScreenActivity implements Candidates.Master{

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(new MasterView());
	}

}
