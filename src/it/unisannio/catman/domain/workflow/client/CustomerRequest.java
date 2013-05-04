package it.unisannio.catman.domain.workflow.client;

import it.unisannio.catman.domain.workflow.Customer;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(Customer.class)
public interface CustomerRequest extends RequestContext {
	Request<CustomerProxy> findByName(String name);
	InstanceRequest<CustomerProxy, Void> persist();
}
