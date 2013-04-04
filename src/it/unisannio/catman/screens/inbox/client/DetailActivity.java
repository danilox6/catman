package it.unisannio.catman.screens.inbox.client;

import it.unisannio.catman.common.client.Path;
import it.unisannio.catman.common.client.ScreenActivity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;

public class DetailActivity extends ScreenActivity implements Inbox.Detail {

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		Path p = getPath();
		panel.setWidget(new Hyperlink(p.toString(), pathTo("inbox").getToken()));
		
	}

}
