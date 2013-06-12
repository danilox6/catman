package it.unisannio.catman.screens.worker.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.ErrorHandler;
import it.unisannio.catman.common.client.ScreenActivity;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.screens.worker.client.Worker.View;
import it.unisannio.catman.screens.worker.client.queries.ContractsQuery;
import it.unisannio.catman.screens.worker.client.queries.PieceworksQuery;

public class DetailActivity extends ScreenActivity implements Worker.Presenter{
	private View view;
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = new DetailView();
		panel.setWidget(view);
		
		view.setPresenter(this);

		findAndSetWorkerProxy(true);
	}

	@Override
	public void setCandidate(WorkerProxy workerProxy, boolean candidate) {
	
		getDataStore().workers().setCandidate(candidate).using(workerProxy).fire(new Receiver<Void>() {

			@Override
			public void onSuccess(Void response) {
				findAndSetWorkerProxy(false);
			}
			
			@Override
			public void onFailure(ServerFailure error) {
				ErrorHandler.handle(error.getMessage()); 
			}
		});
	}
	
	private void findAndSetWorkerProxy(final boolean setQueries){
		try{
			DataStore dataStore = getDataStore();
			EntityProxyId<WorkerProxy> entityId = dataStore.getProxyId(getIntent().get(0, ""));
			dataStore.workers().find(entityId).with("name").fire(new Receiver<WorkerProxy>() {

				@Override
				public void onSuccess(WorkerProxy worker) {
					view.setWorkerProxy(worker);
					if(setQueries){
						view.setPieceworksQuery(new PieceworksQuery(worker));
						view.setContractsQuery(new ContractsQuery(worker));
					}
				}

				@Override
				public void onFailure(ServerFailure error) {
					ErrorHandler.handle(error.getMessage()); 
				}
			});
		}catch(IllegalArgumentException e){
			ErrorHandler.handle(); 
		}
	}
}
