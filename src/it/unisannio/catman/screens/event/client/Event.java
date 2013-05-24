package it.unisannio.catman.screens.event.client;

import com.google.gwt.activity.shared.Activity;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Screen;
import it.unisannio.catman.common.client.Screen.HasDetail;
import it.unisannio.catman.common.client.Screen.HasMaster;
import it.unisannio.catman.domain.workflow.client.EventProxy;

public class Event extends Screen implements HasMaster, HasDetail{
	public static interface Master extends Activity {
		interface View {}
	}
	
	public static interface Detail extends Activity {
		interface View {
			void setEventProxy(EventProxy eventProxy);
		}
	}
	
	
	protected Event() {
		super("Event", "event", Icon.CABINET); //FIXME icona appropriata
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
