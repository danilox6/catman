package it.unisannio.catman.screens.resume.client;

import com.google.gwt.activity.shared.Activity;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Screen;
import it.unisannio.catman.common.client.Screen.HasDetail;

public class Resume extends Screen implements HasDetail{
	public static interface Detail extends Activity {
		interface View {}
	}

	protected Resume() {
		super("Resume", "resume", Icon.CONTACT); 
	}
	
	@Override
	public Activity getDetail(Intent i) {
		return new DetailActivity();
	}

}
