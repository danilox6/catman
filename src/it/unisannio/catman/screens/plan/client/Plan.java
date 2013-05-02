package it.unisannio.catman.screens.plan.client;

import com.google.gwt.activity.shared.Activity;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Screen;
import it.unisannio.catman.common.client.Screen.HasMaster;
import it.unisannio.catman.common.client.Screen.HasDetail;


public class Plan extends Screen  implements HasMaster, HasDetail{
	public static interface Master extends Activity {
		interface View {}
	}
	
	public static interface Detail extends Activity {
		interface View {}
	}
	
	public Plan() {
		super("Plan","plan",Icon.GEAR); //FIXME icona appropriata
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
