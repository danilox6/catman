package it.unisannio.catman.screens.personnelmanager.client;

import java.util.List;

import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.ScreenActivity;
import it.unisannio.catman.domain.humanresources.client.WorkersSource;
import it.unisannio.catman.domain.humanresources.client.WorkersSource.Source;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.Receiver;

public class MasterActivity extends ScreenActivity implements PersonnelManager.Presenter {
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		
		final PersonnelManager.View view = new MasterView();
		
		view.setPresenter(this);
		
		WorkersSource.findWorkersSources(new Receiver<List<WorkersSource>>() {

			@Override
			public void onSuccess(List<WorkersSource> workersSources) {
				view.setWorkersSources(workersSources);
			}
		});
		
		panel.setWidget(view);
	}

	@Override
	public void goToWorkersScreen(WorkersSource w) {
		String [] params;
		if(w.getSource() == Source.JOB_BOARD)
			params = new String[]{w.getSource().toString(),w.getJobBoardHystoryToken()};
		else
			params = new String[]{w.getSource().toString()};
		goTo(new Intent("workers").withParams(params));
	}

}
