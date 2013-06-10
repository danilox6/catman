package it.unisannio.catman.domain.humanresources.client;

import java.util.List;

import it.unisannio.catman.domain.humanresources.Worker;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(Worker.class)
public interface WorkerRequest extends RequestContext {
	InstanceRequest<WorkerProxy, Void> persist(); 
	
	Request<List<WorkerProxy>> findAll();
	Request<WorkerProxy> findWorker(Long id);
	
	
	Request<List<WorkerProxy>> listAll(int start, int length);
	Request<Integer> count();
	
	Request<List<WorkerProxy>> listWorkersSource(int start, int length);
	Request<List<WorkerProxy>> listCandidates(int start, int length);
	Request<List<WorkerProxy>> listByJobBoard(JobBoardProxy jobBoard, int start, int length);
	
	Request<Integer> countCandidates();
	Request<Integer> countWorkersSource();
	Request<Integer> countByJobBoard(JobBoardProxy jobBoard);
}
