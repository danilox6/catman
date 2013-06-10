package it.unisannio.catman.domain.humanresources.client;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;

import it.unisannio.catman.common.client.App;

public class WorkersSource implements EntityProxy{
	public static enum Source {WORKERS, CANDIDATES, JOB_BOARD};

	private Source sourceType;
	private String jobBoardToken;
	private String name;
	private int count = -1;

	public WorkersSource(Source listType){
		this.sourceType = listType;
		if(listType == Source.WORKERS)
			name = "Workers";
		else if(listType == Source.CANDIDATES)
			name = "Candidates";
		else
			throw new IllegalArgumentException();
	}
	
	public WorkersSource(JobBoardProxy jobBoard){
		this.sourceType = Source.JOB_BOARD;
		this.name = jobBoard.getName();
		this.jobBoardToken = App.getInstance().getDataStore().getHistoryToken(jobBoard.stableId());
		this.count = jobBoard.getWorkersCount();
	}

	public Source getSourceType() {
		return sourceType;
	}

	public String getJobBoardHystoryToken() {
		return jobBoardToken;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
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
