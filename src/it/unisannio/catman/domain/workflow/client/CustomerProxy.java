package it.unisannio.catman.domain.workflow.client;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

import it.unisannio.catman.domain.workflow.Customer;

@ProxyFor(Customer.class)
public interface CustomerProxy extends EntityProxy {
	String getName();
	void setName(String name);
}
