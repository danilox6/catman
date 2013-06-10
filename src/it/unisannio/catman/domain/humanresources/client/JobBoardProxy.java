package it.unisannio.catman.domain.humanresources.client;

import java.util.List;

import it.unisannio.catman.domain.humanresources.JobBoard;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(JobBoard.class)
public interface JobBoardProxy extends EntityProxy {
	String getName();
	void setName(String name);
	
	List<WorkerProxy> getWorkers();
	
	int getWorkersCount();
	
}
