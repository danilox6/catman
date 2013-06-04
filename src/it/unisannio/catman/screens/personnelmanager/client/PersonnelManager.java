package it.unisannio.catman.screens.personnelmanager.client;

import com.google.gwt.activity.shared.Activity;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Screen;
import it.unisannio.catman.common.client.Screen.HasMaster;
import it.unisannio.catman.screens.personnelmanager.client.MasterActivity;

public class PersonnelManager extends Screen implements HasMaster{
	public static interface Master extends Activity {
		interface View {}
	}

	protected PersonnelManager() {
		super("Personnel", "personnel-manager", Icon.TIE);
	}

	@Override
	public Activity getMaster(Intent i) {
		return new MasterActivity();
	}

}
