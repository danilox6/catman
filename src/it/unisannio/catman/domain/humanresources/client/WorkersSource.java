package it.unisannio.catman.domain.humanresources.client;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;

import it.unisannio.catman.common.client.App;

public class WorkersSource implements EntityProxy{
	public static enum Source {WORKERS, CANDIDATES, JOB_BOARD};

	private Source sourceType;
	private JobBoardProxy jobBoardProxy;
	private int count = -1;

	public WorkersSource(Source sourceType){
		this.sourceType = sourceType;
	}
	
	public WorkersSource(JobBoardProxy jobBoard){
		this.sourceType = Source.JOB_BOARD;
		this.jobBoardProxy = jobBoard; 
	}

	public WorkersSource(String sourceType) {
		this.sourceType = fromString(sourceType);
	}

	public Source getSource() {
		return sourceType;
	}

	public String getJobBoardHystoryToken() {
		if (sourceType == Source.JOB_BOARD)
			return App.getInstance().getDataStore().getHistoryToken(getJobBoardProxy().stableId());
		return null;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		if (sourceType == Source.JOB_BOARD)
			return getJobBoardProxy().getWorkersCount();
		return count;
	}
	
	public String getName() {
		if(sourceType == Source.WORKERS)
			return "Workers";
		if(sourceType == Source.CANDIDATES)
			return "Candidates";
		return getJobBoardProxy().getName();
	}
	
	public JobBoardProxy getJobBoardProxy() {
		if(jobBoardProxy==null)
			throw new IllegalStateException("JobBoardProxy cannot be null if sourceType is JOB_BOARD");
		return jobBoardProxy;
	}
	
	public void setJobBoardProxy(JobBoardProxy jobBoardProxy) {
		this.jobBoardProxy = jobBoardProxy;
	}
	
	public static Source fromString(String string){
		if(string.equals(Source.WORKERS.toString()))
			return Source.WORKERS;
		if(string.equals(Source.CANDIDATES.toString()))
			return Source.CANDIDATES;
		if(string.equals(Source.JOB_BOARD.toString()))
			return Source.JOB_BOARD;
		throw new IllegalArgumentException(string);
	}

	//FIXME Workaround per usare WorkerSource nella DataList
	@Override
	public EntityProxyId<?> stableId() {
		throw new UnsupportedOperationException();
	}

	
}
