package it.unisannio.catman.domain.humanresources.client;

import it.unisannio.catman.domain.humanresources.FreelanceContract;
import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(FreelanceContract.class)
public interface FreelanceContractRequest extends RequestContext{
	InstanceRequest<FreelanceContractProxy, Void> persist();

}
