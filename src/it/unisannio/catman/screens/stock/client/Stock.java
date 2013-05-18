package it.unisannio.catman.screens.stock.client;

import com.google.gwt.activity.shared.Activity;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Screen;
import it.unisannio.catman.common.client.Screen.HasDetail;
import it.unisannio.catman.common.client.Screen.HasMaster;

public class Stock extends Screen implements HasDetail, HasMaster{
	public static interface Detail extends Activity {
		interface View {}
	}
	public static interface Master extends Activity {
		interface View {}
	}

	protected Stock() {
		super("Stock", "stock", Icon.CABINET); //FIXME icona appropriata
	}

	@Override
	public Activity getDetail(Intent i) {
		return new DetailActivity();
	}
	
	@Override
	public Activity getMaster(Intent i) {
		return new MasterActivity();
	}
}
