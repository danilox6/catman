package it.unisannio.catman.screens.materialmanager.client;

import com.google.gwt.activity.shared.Activity;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Screen;
import it.unisannio.catman.common.client.Screen.HasMaster;

public class MaterialManager extends Screen implements HasMaster{
	public static interface Master extends Activity {
		interface View {}
	}

	protected MaterialManager() {
		super("Material Manager", "material-manager", Icon.CABINET); //FIXME icona appropriata
	}

	@Override
	public Activity getMaster(Intent i) {
		return new MasterActivity();
	}

}
