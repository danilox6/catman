package it.unisannio.catman.screens.workers.client;

import java.util.List;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.IsWidget;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Screen;
import it.unisannio.catman.common.client.Screen.HasDetail;
import it.unisannio.catman.common.client.Screen.HasMaster;
import it.unisannio.catman.domain.humanresources.client.QualificationProxy;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.domain.humanresources.client.WorkersSource;

public class Workers extends Screen implements HasMaster, HasDetail{
	public static interface View extends IsWidget{
		void setQualificationsList(List<QualificationProxy> qualifications);
		void setWorkersSource(WorkersSource workersSource);
		void setPresenter(Presenter presenter);
	}
	
	public static interface Presenter{
		void goToWorker(WorkerProxy workerProxy);
	}
	
	protected Workers() {
		super("Workers", "workers", Icon.OFFICE_CHAIR); 
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
