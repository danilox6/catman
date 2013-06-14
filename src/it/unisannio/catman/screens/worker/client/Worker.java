package it.unisannio.catman.screens.worker.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.IsWidget;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Screen;
import it.unisannio.catman.common.client.Screen.HasDetail;
import it.unisannio.catman.domain.humanresources.client.ContractProxy;
import it.unisannio.catman.domain.humanresources.client.EmploymentContractProxy;
import it.unisannio.catman.domain.humanresources.client.FreelanceContractProxy;
import it.unisannio.catman.domain.humanresources.client.PieceworkProxy;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.domain.planning.client.PositionProxy;
import it.unisannio.catman.screens.worker.client.queries.ContractsQuery;
import it.unisannio.catman.screens.worker.client.queries.PieceworksQuery;

public class Worker extends Screen implements HasDetail{
	public static interface View extends IsWidget{
		void setWorkerProxy(WorkerProxy workerProxy);
		void setContractsQuery(ContractsQuery contractsQuery);
		void setPieceworksQuery(PieceworksQuery pieceworksQuery);
		void setPresenter(Presenter presenter);
		void setPositionProxy(PositionProxy position);
		void refreshContracts();
	}
	
	public static interface Presenter{
		void setCandidate(WorkerProxy workerProxy, boolean candidate);
		void assignFreelanceToPosition(PieceworkProxy piecework);
		void assignEmployeeToPosition(EmploymentContractProxy piecework);
	}
	
	protected Worker() {
		super("Worker", "worker", Icon.CONTACT); 
	}

	@Override
	public Activity getDetail(Intent i) {
		return new DetailActivity();
	}
}
