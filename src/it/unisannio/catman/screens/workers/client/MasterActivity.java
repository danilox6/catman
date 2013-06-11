package it.unisannio.catman.screens.workers.client;

import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.ErrorHandler;
import it.unisannio.catman.common.client.ScreenActivity;
import it.unisannio.catman.domain.humanresources.client.JobBoardProxy;
import it.unisannio.catman.domain.humanresources.client.QualificationProxy;
import it.unisannio.catman.domain.humanresources.client.QualificationRequest;
import it.unisannio.catman.domain.humanresources.client.WorkersSource;
import it.unisannio.catman.domain.humanresources.client.WorkersSource.Source;

public class MasterActivity extends ScreenActivity implements Workers.Presenter{

	Workers.View view;	
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		
		view = new MasterView();
		panel.setWidget(view);
		
		final WorkersSource workersSource = new WorkersSource(getIntent().get(0, ""));

		try{
			DataStore dataStore = getDataStore();
			final QualificationRequest qualificationRequest = dataStore.qualifications();
			if(workersSource.getSource() == Source.JOB_BOARD){
				EntityProxyId<JobBoardProxy> jobBoardId = dataStore.getProxyId(getIntent().get(1, ""));
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
}
