package it.unisannio.catman.screens.worker.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.IsWidget;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Screen;
import it.unisannio.catman.common.client.Screen.HasDetail;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.screens.worker.client.queries.ContractsQuery;
import it.unisannio.catman.screens.worker.client.queries.QualificationsQuery;

public class Worker extends Screen implements HasDetail{
	public static interface View extends IsWidget{
		void setWorkerProxy(WorkerProxy workerProxy);
		void setContractsQuery(ContractsQuery contractsQuery);
		void setQualificationsQuery(QualificationsQuery qualificationsQuery);
	}
	
	public static interface Presenter{
	}
	
	protected Worker() {
		super("Worker", "worker", Icon.CONTACT); 
	}

	@Override
	public Activity getDetail(Intent i) {
		return new DetailActivity();
	}
}
