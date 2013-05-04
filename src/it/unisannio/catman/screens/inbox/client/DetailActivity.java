package it.unisannio.catman.screens.inbox.client;

import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.ScreenActivity;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class DetailActivity extends ScreenActivity implements Inbox.Detail {
	DataStore dataStore = GWT.create(DataStore.class);
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		//Path p = getPath();
	//	panel.setWidget(new Hyperlink(p.toString(), pathTo("inbox").getToken()));
		panel.setWidget(new DetailView());
	}

}
