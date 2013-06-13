package it.unisannio.catman.screens.personnelpicker.client.queries;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.domain.planning.client.PositionProxy;

public class WorkersByPositionQuery implements Query<WorkerProxy>{
	private static final DataStore dataStore = App.getInstance().getDataStore();

	private PositionProxy position;

	public WorkersByPositionQuery(PositionProxy position) {
		this.position = position;
	}

	@Override
	public Request<List<WorkerProxy>> list(int start, int length) {
		return dataStore.workers().listByQualification(position.getQualification(), start, length);
	}

	@Override
	public Request<Integer> count() {
		return dataStore.workers().countByQualification(position.getQualification());
	}

	@Override
	public Request<Void> deleteAll(List<WorkerProxy> skip) {
		throw new UnsupportedOperationException(); //FIXME
	}

	@Override
	public Request<Void> deleteSet(List<WorkerProxy> set) {
		throw new UnsupportedOperationException(); //FIXME
	}

}
