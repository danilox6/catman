package it.unisannio.catman.inbox.client;

import com.google.gwt.activity.shared.Activity;

import it.unisannio.catman.client.Unit;

public class Inbox extends Unit {

	public Inbox() {
		super("Richieste", Icon.INBOX, 0);
	}

	@Override
	public Activity getDefaultMasterActivity() {
		return new MasterActivity();
	}

	@Override
	public Activity getDetailActivity(String name, long id) {
		return new DetailActivity();
	}
}
