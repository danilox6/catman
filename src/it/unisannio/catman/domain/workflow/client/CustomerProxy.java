package it.unisannio.catman.domain.workflow.client;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

import it.unisannio.catman.domain.contacts.client.IsContactable;
import it.unisannio.catman.domain.workflow.Customer;

@ProxyFor(Customer.class)
public interface CustomerProxy extends EntityProxy, IsContactable {
	List<EventProxy> getEvents();
}
