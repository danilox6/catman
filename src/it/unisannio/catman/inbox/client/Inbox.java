package it.unisannio.catman.inbox.client;

import com.google.gwt.activity.shared.Activity;

import it.unisannio.catman.client.Icon;
import it.unisannio.catman.client.Intent;
import it.unisannio.catman.client.Screen;

import it.unisannio.catman.client.Screen.HasMaster;
import it.unisannio.catman.client.Screen.HasDetail;

public class Inbox extends Screen implements HasMaster, HasDetail {
	public static interface Master extends Activity {
		interface View {}
	}
	
	public static interface Detail extends Activity {
		interface View {}
	}

	public Inbox() {
		super("Richieste", "inbox", Icon.INBOX, 0);
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
