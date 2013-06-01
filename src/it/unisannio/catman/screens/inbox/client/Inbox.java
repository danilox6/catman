package it.unisannio.catman.screens.inbox.client;

import com.google.gwt.activity.shared.Activity;


import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Screen;
import it.unisannio.catman.common.client.Screen.HasDetail;
import it.unisannio.catman.common.client.Screen.HasMaster;

public class Inbox extends Screen implements HasMaster, HasDetail {
	public static interface Master extends Activity {
		
		void openNewDialog();
		interface View {}
	}
	
	public static interface Detail extends Activity {
		interface View {}
	}

	public Inbox() {
		super("Richieste", "inbox", Icon.INBOX);
	}

	@Override
	public Activity getMaster(Intent i) {
		return new MasterActivity();
	}

	@Override
	public Activity getDetail(Intent i) {
		return new DetailActivity();
	}
}
