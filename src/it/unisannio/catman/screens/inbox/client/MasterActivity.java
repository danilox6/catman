package it.unisannio.catman.screens.inbox.client;

import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Trail;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;

public class MasterActivity extends AbstractActivity implements Inbox.Master {
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(new Hyperlink("Go to detail", Trail.to("inbox").getToken()));

	}

}
