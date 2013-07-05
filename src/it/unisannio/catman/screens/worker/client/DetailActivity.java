package it.unisannio.catman.screens.worker.client;

import java.util.Set;

import javax.validation.ConstraintViolation;

import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.ErrorHandler;
import it.unisannio.catman.common.client.ScreenActivity;
import it.unisannio.catman.domain.humanresources.client.EmploymentContractProxy;
import it.unisannio.catman.domain.humanresources.client.FreelanceContractProxy;
import it.unisannio.catman.domain.humanresources.client.FreelanceContractRequest;
import it.unisannio.catman.domain.humanresources.client.PieceworkProxy;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.domain.planning.client.PositionProxy;
import it.unisannio.catman.screens.worker.client.Worker.View;
import it.unisannio.catman.screens.worker.client.queries.ContractsQuery;
import it.unisannio.catman.screens.worker.client.queries.PieceworksQuery;

public class DetailActivity extends ScreenActivity implements Worker.Presenter{
	private View view;
	private PositionProxy position;

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = new DetailView();
		panel.setWidget(view);

		view.setPresenter(this);

		findAndSetWorkerProxy(true);

		try{
			DataStore dataStore = getDataStore();
			EntityProxyId<PositionProxy> entityId = dataStore.getProxyId(getIntent().get(1, ""));
			dataStore.positions().find(entityId).with("qualification").fire(new Receiver<PositionProxy>() {

				@Override
				public void onSuccess(PositionProxy response) {
					DetailActivity.this.position = response;
					view.setPositionProxy(position);
				}

				@Override
				public void onFailure(ServerFailure error) {
					ErrorHandler.handle(error.getMessage());
				}
			});
		}catch (IllegalArgumentException e){

		}
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

	@Override
	public void assignFreelanceToPosition(PieceworkProxy piecework) {
		FreelanceContractRequest freelances = getDataStore().freelanceContracts();
		FreelanceContractProxy freelance = freelances.create(FreelanceContractProxy.class);
		freelance.setPiecework(piecework);
		freelance.setPosition(position);
		freelances.persist().using(freelance).fire(new WorkerAssigmentReceiver());
	}

	@Override
	public void assignEmployeeToPosition(EmploymentContractProxy contract) {
		getDataStore().employmentContracts().assignTo(position).using(contract).fire(new WorkerAssigmentReceiver());

	}

	private class WorkerAssigmentReceiver extends Receiver<Void>{
		@Override
		public void onSuccess(Void response) {
			goUp();
			//view.refreshContracts();
		}
		
		@Override
		public void onFailure(ServerFailure error) {
			if(error.getMessage()!= null){
				String message = error.getMessage();
				if(message.startsWith("Server Error:")){
					message = message.substring("Server Error:".length());
				}
				view.showAlert(message, AlertType.ERROR);
			}
			else
				view.showAlert("An error occurred during the assignment :(", AlertType.ERROR);
		}
		@Override
		public void onConstraintViolation(Set<ConstraintViolation<?>> violations) {
			for(ConstraintViolation<?> v : violations) {
				view.showAlert(v.getMessage(), AlertType.ERROR);
			}
		}
		
	}
}
