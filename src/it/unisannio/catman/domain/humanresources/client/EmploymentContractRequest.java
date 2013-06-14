package it.unisannio.catman.domain.humanresources.client;

import it.unisannio.catman.domain.humanresources.EmploymentContract;
import it.unisannio.catman.domain.planning.client.PositionProxy;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(EmploymentContract.class)
public interface EmploymentContractRequest extends RequestContext{
	InstanceRequest<EmploymentContractProxy, Void> persist();
	InstanceRequest<EmploymentContractProxy, Void> assignTo(PositionProxy p);
}
