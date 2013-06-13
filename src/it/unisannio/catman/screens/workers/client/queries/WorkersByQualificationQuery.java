package it.unisannio.catman.screens.workers.client.queries;

import it.unisannio.catman.common.client.AbstractQuery;
import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.domain.humanresources.client.QualificationProxy;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.domain.humanresources.client.WorkersSource;
import it.unisannio.catman.domain.humanresources.client.WorkersSource.Source;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;

public class WorkersByQualificationQuery extends AbstractQuery<WorkerProxy>{
	private static final DataStore dataStore = App.getInstance().getDataStore();
	
	private QualificationProxy qualification;
	private WorkersSource source;
	
	public WorkersByQualificationQuery(QualificationProxy qualification, WorkersSource source) {
		this.qualification = qualification;
		this.source = source;
	}

	@Override
	public Request<List<WorkerProxy>> list(int start, int length) {
		if(source.getSource() == Source.WORKERS)
			return dataStore.workers().listByQualificationInWorkersSource(qualification, start, length);
		if(source.getSource() == Source.CANDIDATES)
			return dataStore.workers().listByQualificationInCandidates(qualification, start, length);
		return dataStore.workers().listByQualificationInJobBoard(qualification, source.getJobBoardProxy(), start, start);
	}
	
	@Override
	public Request<Integer> count() {
		if(source.getSource() == Source.WORKERS)
			return dataStore.workers().countByQualificationInWorkersSource(qualification);
		if(source.getSource() == Source.CANDIDATES)
			return dataStore.workers().countByQualificationInCandidates(qualification);
		return dataStore.workers().countByQualificationInJobBoard(qualification, source.getJobBoardProxy());
	}
}
