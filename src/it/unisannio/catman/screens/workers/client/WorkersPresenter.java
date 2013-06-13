package it.unisannio.catman.screens.workers.client;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.ErrorHandler;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.ScreenActivity;
import it.unisannio.catman.domain.humanresources.client.JobBoardProxy;
import it.unisannio.catman.domain.humanresources.client.QualificationProxy;
import it.unisannio.catman.domain.humanresources.client.QualificationRequest;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.domain.humanresources.client.WorkersSource;
import it.unisannio.catman.domain.humanresources.client.WorkersSource.Source;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class WorkersPresenter implements Workers.Presenter{
	
	private Workers.View view;
	private ScreenActivity screenActivity;
	
	public WorkersPresenter(Workers.View v, ScreenActivity activity, Intent intent) {
		this.view = v;
		this.screenActivity = activity;
		
		final WorkersSource workersSource = new WorkersSource(intent.get(0, ""));

		try{
			DataStore dataStore = App.getInstance().getDataStore();
			final QualificationRequest qualificationRequest = dataStore.qualifications();
			if(workersSource.getSource() == Source.JOB_BOARD){
				EntityProxyId<JobBoardProxy> jobBoardId = dataStore.getProxyId(intent.get(1, ""));
				dataStore.jobBoards().find(jobBoardId).fire(new Receiver<JobBoardProxy>() {
					public void onSuccess(JobBoardProxy jobBoard) {
						workersSource.setJobBoardProxy(jobBoard);
						view.setWorkersSource(workersSource);
						qualificationRequest.findByJobBoard(jobBoard).fire(new QualificationsReceiver());
					}
					public void onFailure(ServerFailure error) {
						ErrorHandler.handle(error.getMessage()); 
					}
				});

			}else{
				view.setWorkersSource(workersSource);
				
				Request<List<QualificationProxy>> req;
				if (workersSource.getSource() == Source.WORKERS)
					req = qualificationRequest.findInWorkersSource();
				else
					req = qualificationRequest.findInCandidates();
				req.with("qualifications").fire(new QualificationsReceiver());
			}
		}catch(IllegalArgumentException e){
			ErrorHandler.handle(); 
		}
	}


	class QualificationsReceiver extends Receiver<List<QualificationProxy>>{
		
		@Override
		public void onSuccess(List<QualificationProxy> qualifications) {
			view.setQualificationsList(qualifications);
		}
		
		@Override
		public void onFailure(ServerFailure error) {
			ErrorHandler.handle(error.getMessage()); 
		}

	}


	@Override
	public void goToWorkerScreen(WorkerProxy workerProxy) {
		String id = App.getInstance().getDataStore().getHistoryToken(workerProxy.stableId());
		screenActivity.goTo(new Intent("worker").withParams(id));
	}
	
	
}
