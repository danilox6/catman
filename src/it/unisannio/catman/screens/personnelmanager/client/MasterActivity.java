package it.unisannio.catman.screens.personnelmanager.client;

import java.util.ArrayList;
import java.util.List;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.ScreenActivity;
import it.unisannio.catman.domain.humanresources.client.JobBoardProxy;
import it.unisannio.catman.domain.humanresources.client.WorkersSource;
import it.unisannio.catman.domain.humanresources.client.WorkersSource.ListType;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.Receiver;

public class MasterActivity extends ScreenActivity implements PersonnelManager.Master {
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		
		final PersonnelManager.Master.View masterView = new MasterView(this);
		
		final DataStore dataStore = App.getInstance().getDataStore();
		
		final List<WorkersSource> workersLists = new ArrayList<WorkersSource>();
		
		final WorkersSource workersWList = new WorkersSource(ListType.WORKERS);
		final WorkersSource candidatesWList = new WorkersSource(ListType.CANDIDATES);
		
		workersLists.add(workersWList);
		workersLists.add(candidatesWList);
		
		dataStore.workers().countWorkersSource().fire(new Receiver<Integer>() {

			@Override
			public void onSuccess(Integer response) {
				workersWList.setCount(response);
				masterView.setWorkersSources(workersLists);
			}
		});
		
		dataStore.workers().countCandidates().fire(new Receiver<Integer>() {

			@Override
			public void onSuccess(Integer response) {
				candidatesWList.setCount(response);
				masterView.setWorkersSources(workersLists);
			}
		});
		
		dataStore.jobBoards().findAll().fire(new Receiver<List<JobBoardProxy>>() {

			@Override
			public void onSuccess(List<JobBoardProxy> jobBoards) {
				for (JobBoardProxy jobBoard : jobBoards) 
					workersLists.add(new WorkersSource(jobBoard));
				masterView.setWorkersSources(workersLists);
			}
		});
		
		
		panel.setWidget(masterView);
	}

	@Override
	public void goToWorkersScreen(WorkersSource w) {
		goTo(new Intent("workers").withParams(w.toString()));
	}

}
