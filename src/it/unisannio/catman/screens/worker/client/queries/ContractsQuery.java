package it.unisannio.catman.screens.worker.client.queries;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.domain.humanresources.client.ContractProxy;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;

public class ContractsQuery implements Query<ContractProxy> {
	private static final DataStore dataStore = App.getInstance().getDataStore();

	private WorkerProxy worker;
	
	public ContractsQuery(WorkerProxy worker) {
		this.worker = worker;
	}
	
	@Override
	public Request<List<ContractProxy>> list(int start, int length) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Request<Integer> count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Request<Void> deleteAll(List<ContractProxy> skip) {
		throw new UnsupportedOperationException(); //FIXME
	}

	@Override
	public Request<Void> deleteSet(List<ContractProxy> set) {
		throw new UnsupportedOperationException(); //FIXME
	}

}
