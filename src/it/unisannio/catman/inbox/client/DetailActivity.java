package it.unisannio.catman.inbox.client;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HTML;

public class DetailActivity extends AbstractActivity implements Inbox.Detail {

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(new HTML("Hello Detail!"));
		
	}

}
