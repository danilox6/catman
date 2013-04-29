package it.unisannio.catman.screens.materialsmanager.client;

import com.google.gwt.activity.shared.Activity;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Screen;
import it.unisannio.catman.common.client.Screen.HasMaster;

public class MaterialsManager extends Screen implements HasMaster{
	public static interface Master extends Activity {
		interface View {}
	}

	protected MaterialsManager() {
		super("Materials Manager", "materials-manager", Icon.CABINET); //FIXME icona appropriata
	}

	@Override
	public Activity getMaster(Intent i) {
		return new MasterActivity();
	}

}
