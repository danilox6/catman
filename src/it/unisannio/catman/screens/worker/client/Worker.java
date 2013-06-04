package it.unisannio.catman.screens.worker.client;

import com.google.gwt.activity.shared.Activity;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Screen;
import it.unisannio.catman.common.client.Screen.HasDetail;

public class Worker extends Screen implements HasDetail{
	public static interface Detail extends Activity {
		interface View {}
	}
	
	protected Worker() {
		super("Worker", "worker", Icon.CONTACT); 
	}

	@Override
	public Activity getDetail(Intent i) {
		return new DetailActivity();
	}
}
