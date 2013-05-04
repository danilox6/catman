package it.unisannio.catman.screens.inbox.client;

import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.ScreenActivity;
import it.unisannio.catman.domain.workflow.client.CustomerProxy;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class DetailActivity extends ScreenActivity implements Inbox.Detail {
	DataStore dataStore = GWT.create(DataStore.class);
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		//Path p = getPath();
	//	panel.setWidget(new Hyperlink(p.toString(), pathTo("inbox").getToken()));
		panel.setWidget(new DetailView());
	}

}
