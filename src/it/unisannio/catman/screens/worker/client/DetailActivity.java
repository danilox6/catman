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
import it.unisannio.catman.screens.worker.client.queries.ContractsQuery;
import it.unisannio.catman.screens.worker.client.queries.QualificationsQuery;

public class DetailActivity extends ScreenActivity implements Worker.Presenter{

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		final Worker.View view = new DetailView();
		panel.setWidget(view);

		try{
			DataStore dataStore = getDataStore();
			EntityProxyId<WorkerProxy> entityId = dataStore.getProxyId(getIntent().get(0, ""));
			dataStore.workers().find(entityId).with("name").fire(new Receiver<WorkerProxy>() {

				@Override
				public void onSuccess(WorkerProxy worker) {
					view.setWorkerProxy(worker);
					view.setQualificationsQuery(new QualificationsQuery(worker));
					view.setContractsQuery(new ContractsQuery(worker));
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
