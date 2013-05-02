package it.unisannio.catman.screens.materialmanager.client;

import it.unisannio.catman.common.client.ScreenActivity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class MasterActivity extends ScreenActivity implements MaterialManager.Master {
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(new MasterView());
	}

}
