package it.unisannio.catman.screens.jobboards.client;

import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Screen;

import com.google.gwt.activity.shared.Activity;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Screen.HasDetail;
import it.unisannio.catman.common.client.Screen.HasMaster;

public class JobBoards extends Screen implements HasMaster, HasDetail {
	
	public static interface Master extends Activity {
		interface View {}
	}
	
	public static interface Detail extends Activity {
		interface View {}
	}


	protected JobBoards() {
		super("Job Boards", "job-boards", Icon.PEOPLE); //FIXME icona appropriata
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
