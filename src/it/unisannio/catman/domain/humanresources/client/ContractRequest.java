package it.unisannio.catman.domain.humanresources.client;

import java.util.List;

import it.unisannio.catman.domain.humanresources.Contract;

import com.google.web.bindery.requestfactory.shared.ExtraTypes;
import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(Contract.class)
@ExtraTypes({FreelanceContractProxy.class, EmploymentContractProxy.class})
public interface ContractRequest extends RequestContext{
	InstanceRequest<ContractProxy, Void> persist();
	Request<List<ContractProxy>> listByWorker(WorkerProxy workerProxy,int start, int length);
	Request<Integer> countdByWorker(WorkerProxy worker);
}
