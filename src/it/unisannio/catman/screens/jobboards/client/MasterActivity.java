package it.unisannio.catman.screens.jobboards.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import it.unisannio.catman.common.client.ScreenActivity;

public class MasterActivity extends ScreenActivity implements JobBoards.Master{

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(new MasterView());
	}

}
