package it.unisannio.catman.screens.event.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.IsWidget;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Screen;
import it.unisannio.catman.common.client.Screen.HasDetail;
import it.unisannio.catman.common.client.Screen.HasMaster;
import it.unisannio.catman.domain.workflow.client.EventProxy;

public class Event extends Screen implements HasMaster, HasDetail{
	public static interface Master extends Activity {
		interface View extends IsWidget{
			
		}
	}
	
	public static interface Detail extends Activity {
		interface View extends IsWidget{
			void setEventProxy(EventProxy eventProxy);
		}
	}
	
	
	protected Event() {
		super("Event", "event", Icon.NOTES);
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
