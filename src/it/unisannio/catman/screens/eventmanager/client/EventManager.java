package it.unisannio.catman.screens.eventmanager.client;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Screen;
import it.unisannio.catman.common.client.Screen.HasMaster;

import com.google.gwt.activity.shared.Activity;

public class EventManager extends Screen implements HasMaster{
	public static interface Master extends Activity {
		interface View {}
	}
	
	public static interface Detail extends Activity {
		interface View {}
	}

	protected EventManager() {
		super("Event Manager", "event-manager", Icon.CALENDAR);
	}

	@Override
	public Activity getMaster(Intent i) {
		return new MasterActivity();
	}
	

}
