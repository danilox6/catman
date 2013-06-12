package it.unisannio.catman.domain.humanresources.client;

import java.util.List;

import it.unisannio.catman.domain.humanresources.Piecework;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(Piecework.class)
public interface PieceworkRequest extends RequestContext{
	InstanceRequest<PieceworkProxy,Void> persist();
	
	Request<List<PieceworkProxy>> listByWorker(WorkerProxy worker, int start, int length);
	Request<Integer> countByWorker(WorkerProxy worker);
}
