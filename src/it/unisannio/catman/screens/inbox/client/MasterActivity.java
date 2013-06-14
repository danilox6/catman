package it.unisannio.catman.screens.inbox.client;

import it.unisannio.catman.common.client.ScreenActivity;
import it.unisannio.catman.screens.warehouse.client.editors.WarehouseEditor;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class MasterActivity extends ScreenActivity implements Inbox.Master {
	
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
	//	Path p = getPath();
	//	Intent in = new Intent("inbox").withParams(String.valueOf(p.size() + 1));
		//panel.setWidget(new Hyperlink(p.toString(), pathTo(in).getToken()));
		panel.setWidget(new MasterView(this));
	}
	

	@Override
	public void openNewDialog() {
		new WarehouseEditor().open();
		
	}

}
