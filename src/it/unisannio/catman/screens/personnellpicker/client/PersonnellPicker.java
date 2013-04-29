package it.unisannio.catman.screens.personnellpicker.client;

import com.google.gwt.activity.shared.Activity;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Screen;
import it.unisannio.catman.common.client.Screen.HasMaster;
import it.unisannio.catman.common.client.Screen.HasDetail;


public class PersonnellPicker extends Screen implements HasMaster, HasDetail{
	public static interface Master extends Activity {
		interface View {}
	}
	
	public static interface Detail extends Activity {
		interface View {}
	}
	

	protected PersonnellPicker() {
		super("Personnell Picker", "personnell-picker", Icon.PEOPLE);
		// TODO Auto-generated constructor stub
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
