package it.unisannio.catman.domain.humanresources.client;

import java.util.Set;

import it.unisannio.catman.domain.contacts.client.IsContactable;
import it.unisannio.catman.domain.humanresources.Worker;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Worker.class)
public interface WorkerProxy extends EntityProxy, IsContactable {
	Set<ContractProxy> getContracts();
	
	String getResume();
	void setResume(String r);
}
