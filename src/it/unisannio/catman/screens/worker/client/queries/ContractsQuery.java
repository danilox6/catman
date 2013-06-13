package it.unisannio.catman.screens.worker.client.queries;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;

import it.unisannio.catman.common.client.AbstractQuery;
import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.domain.humanresources.client.ContractProxy;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;

public class ContractsQuery extends AbstractQuery<ContractProxy> {
	private static final DataStore dataStore = App.getInstance().getDataStore();

	private WorkerProxy worker;
	
	public ContractsQuery(WorkerProxy worker) {
		this.worker = worker;
	}
	
	@Override
	public Request<List<ContractProxy>> list(int start, int length) {
		return dataStore.contracts().listByWorker(worker, start, length).with("piecework.wage","piecework.qualification.name");
	}

	@Override
	public Request<Integer> count() {
		return dataStore.contracts().countdByWorker(worker);
	}
}
