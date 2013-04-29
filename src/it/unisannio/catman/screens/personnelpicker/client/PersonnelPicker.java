package it.unisannio.catman.screens.personnelpicker.client;

import com.google.gwt.activity.shared.Activity;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Screen;
import it.unisannio.catman.common.client.Screen.HasMaster;
import it.unisannio.catman.common.client.Screen.HasDetail;


public class PersonnelPicker extends Screen implements HasMaster, HasDetail{
	public static interface Master extends Activity {
		interface View {}
	}
	
	public static interface Detail extends Activity {
		interface View {}
	}
	

	protected PersonnelPicker() {
		super("Personnel Picker", "personnel-picker", Icon.PEOPLE);
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
