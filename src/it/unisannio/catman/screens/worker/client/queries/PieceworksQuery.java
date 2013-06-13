package it.unisannio.catman.screens.worker.client.queries;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;

import it.unisannio.catman.common.client.AbstractQuery;
import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.domain.humanresources.client.PieceworkProxy;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;

public class PieceworksQuery extends AbstractQuery<PieceworkProxy>{
	
	private static final DataStore dataStore = App.getInstance().getDataStore();
	
	private WorkerProxy worker;
	
	public PieceworksQuery(WorkerProxy worker) {
		this.worker = worker;
	}

	@Override
	public Request<List<PieceworkProxy>> list(int start, int length) {
		return dataStore.pieceworks().listByWorker(worker, start, length).with("qualification.name");
	}

	@Override
	public Request<Integer> count() {
		return dataStore.pieceworks().countByWorker(worker);
	}
}
