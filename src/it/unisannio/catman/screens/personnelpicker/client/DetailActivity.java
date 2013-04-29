package it.unisannio.catman.screens.personnelpicker.client;

import it.unisannio.catman.common.client.ScreenActivity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class DetailActivity extends ScreenActivity implements PersonnelPicker.Detail {

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		//Path p = getPath();
	//	panel.setWidget(new Hyperlink(p.toString(), pathTo("inbox").getToken()));
		panel.setWidget(new DetailView());
	}

}
