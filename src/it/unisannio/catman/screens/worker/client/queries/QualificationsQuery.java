package it.unisannio.catman.screens.worker.client.queries;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.domain.humanresources.client.QualificationProxy;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;

public class QualificationsQuery implements Query<QualificationProxy>{
	private static final DataStore dataStore = App.getInstance().getDataStore();
	
	private WorkerProxy worker;
	
	public QualificationsQuery(WorkerProxy worker) {
		this.worker = worker;
	}

	@Override
	public Request<List<QualificationProxy>> list(int start, int length) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Request<Integer> count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Request<Void> deleteAll(List<QualificationProxy> skip) {
		throw new UnsupportedOperationException(); //FIXME
	}

	@Override
	public Request<Void> deleteSet(List<QualificationProxy> set) {
		throw new UnsupportedOperationException(); //FIXME
	}

}
