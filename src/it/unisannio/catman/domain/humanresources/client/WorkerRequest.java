package it.unisannio.catman.domain.humanresources.client;

import java.util.List;

import it.unisannio.catman.domain.humanresources.Worker;
import it.unisannio.catman.domain.planning.client.PositionProxy;

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
	
	Request<List<WorkerProxy>> listInWorkersSource(int start, int length);
	Request<List<WorkerProxy>> listInCandidates(int start, int length);
	Request<List<WorkerProxy>> listByJobBoard(JobBoardProxy jobBoard, int start, int length);
	
	Request<Integer> countInCandidates();
	Request<Integer> countInWorkersSource();
	Request<Integer> countByJobBoard(JobBoardProxy jobBoard);
	
	Request<List<WorkerProxy>> listByQualificationInWorkersSource(QualificationProxy qualification, int start, int length);
	Request<Integer> countByQualificationInWorkersSource(QualificationProxy qualification);
	
	Request<List<WorkerProxy>> listByQualificationInCandidates(QualificationProxy qualification, int start, int length);
	Request<Integer> countByQualificationInCandidates(QualificationProxy qualification);
	
	Request<List<WorkerProxy>> listByQualificationInJobBoard(QualificationProxy qualification, JobBoardProxy jobBoard, int start, int length);
	Request<Integer> countByQualificationInJobBoard(QualificationProxy qualification, JobBoardProxy jobBoard);
	
	InstanceRequest<WorkerProxy, Boolean> hasQualification(QualificationProxy qualification);
	
	InstanceRequest<WorkerProxy, Void> setCandidate(boolean candidate);
	
//	InstanceRequest<WorkerProxy, Void> addPiecework(PieceworkProxy p);

	Request<List<WorkerProxy>> findByPosition(PositionProxy position);
	Request<Integer> countByPosition(PositionProxy position);
}
