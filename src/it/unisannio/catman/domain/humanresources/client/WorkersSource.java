package it.unisannio.catman.domain.humanresources.client;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;

import it.unisannio.catman.common.client.App;

public class WorkersSource implements EntityProxy{
	public static enum ListType {WORKERS, CANDIDATES, JOB_BOARD};

	private ListType listType;
	private String jobBoardToken;
	private String name;
	private int count = -1;

	public WorkersSource(ListType listType){
		this.listType = listType;
		if(listType.equals(ListType.WORKERS))
			name = "Workers";
		else if(listType.equals(ListType.CANDIDATES))
			name = "Candidates";
		else
			throw new IllegalArgumentException();
	}
	
	public WorkersSource(JobBoardProxy jobBoard){
		this.listType = ListType.JOB_BOARD;
		this.name = jobBoard.getName();
		this.jobBoardToken = App.getInstance().getDataStore().getHistoryToken(jobBoard.stableId());
		this.count = jobBoard.getWorkersCount();
	}

	private WorkersSource(ListType listType, String historyToken) {
		
		this.listType = listType;
		this.jobBoardToken = historyToken;
	}

	public ListType getListType() {
		return listType;
	}

	public void setListType(ListType listType) {
		this.listType = listType;
	}

	public String getJobBoardHystoryToken() {
		return jobBoardToken;
	}

	private static String htTokenSeparator = "-ht-";
	
	@Override
	public String toString() {
		return listType + (jobBoardToken!=null?htTokenSeparator+jobBoardToken:"");
	}
	
	public static WorkersSource fromString(String string){
		String typeString = string;
		int htSeparatorIndex = string.indexOf(htTokenSeparator);
		if(htSeparatorIndex != -1)
			typeString = string.substring(0, htSeparatorIndex);
		ListType type = null;
		for(ListType t : ListType.values()){
			if(t.toString().equals(typeString)){
				type = t;
				break;
			}
		}
		if(type == null)
			throw new IllegalArgumentException();
		return new WorkersSource(type, htSeparatorIndex!=-1?string.substring(htSeparatorIndex+htTokenSeparator.length()):null);
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

	@Override
	public EntityProxyId<?> stableId() {
		throw new UnsupportedOperationException();
	}

}
