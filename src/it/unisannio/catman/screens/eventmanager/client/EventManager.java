package it.unisannio.catman.screens.eventmanager.client;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.common.client.Screen;
import it.unisannio.catman.common.client.Screen.HasMaster;
import it.unisannio.catman.domain.workflow.client.EventProxy;

import com.google.gwt.activity.shared.Activity;

public class EventManager extends Screen implements HasMaster{
	public static interface Master extends Activity {
		interface View {
			void setEventQuery(Query<EventProxy> query);
		}
	}

	protected EventManager() {
		super("Event Manager", "event-manager", Icon.CALENDAR);
	}

	@Override
	public Activity getMaster(Intent i) {
		return new MasterActivity();
	}
	

}
