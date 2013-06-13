package it.unisannio.catman.screens.supply.client;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Screen;
import it.unisannio.catman.common.client.Screen.HasDetail;

import com.google.gwt.activity.shared.Activity;

public class Supply extends Screen implements HasDetail{
	public static interface Detail extends Activity {
		interface View {}
	}

	protected Supply() {
		super("Supply","supply", Icon.BREAKABLE);
	}

	@Override
	public Activity getDetail(Intent i) {
		return new DetailActivity();
	}

}
