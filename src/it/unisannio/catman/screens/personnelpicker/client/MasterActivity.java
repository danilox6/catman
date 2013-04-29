package it.unisannio.catman.screens.personnelpicker.client;

import it.unisannio.catman.common.client.ScreenActivity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class MasterActivity extends ScreenActivity implements PersonnelPicker.Master {
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(new MasterView());
	}

}
