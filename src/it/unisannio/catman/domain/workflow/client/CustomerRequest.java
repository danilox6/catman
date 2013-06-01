package it.unisannio.catman.domain.workflow.client;

import java.util.List;

import it.unisannio.catman.domain.workflow.Customer;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(Customer.class)
public interface CustomerRequest extends RequestContext {
	Request<CustomerProxy> findByName(String name);
	Request<List<CustomerProxy>> listAll(int start, int length);
	Request<Integer> count();
	InstanceRequest<CustomerProxy, Void> persist();
}
