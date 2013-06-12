package it.unisannio.catman.domain.humanresources.client;

import java.util.List;

import it.unisannio.catman.common.client.HasFindAll;
import it.unisannio.catman.domain.humanresources.Qualification;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(Qualification.class)
public interface QualificationRequest extends RequestContext, HasFindAll<QualificationProxy> {
	InstanceRequest<QualificationProxy,Void> persist();
	
	Request<List<QualificationProxy>> findInWorkersSource();
	
	Request<List<QualificationProxy>> findInCandidates();
	
	Request<List<QualificationProxy>> findByJobBoard(JobBoardProxy jobBoard);
	
	Request<List<QualificationProxy>> findAll();
	
	
}
