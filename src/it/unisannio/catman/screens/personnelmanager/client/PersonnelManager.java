package it.unisannio.catman.screens.personnelmanager.client;

import java.util.List;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.IsWidget;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Screen;
import it.unisannio.catman.common.client.Screen.HasMaster;
import it.unisannio.catman.domain.humanresources.client.WorkersSource;
import it.unisannio.catman.screens.personnelmanager.client.MasterActivity;

public class PersonnelManager extends Screen implements HasMaster{
	public static interface Presenter {
		void goToWorkersScreen(WorkersSource workersSource);
	}
	
	public static interface View extends IsWidget{
		void setWorkersSources(List<WorkersSource> workersSources);
		void setPresenter(Presenter presenter);
	}

	protected PersonnelManager() {
		super("Personnel", "personnel-manager", Icon.TIE);
	}

	@Override
	public Activity getMaster(Intent i) {
		return new MasterActivity();
	}

}
