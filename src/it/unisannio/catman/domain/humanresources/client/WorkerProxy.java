package it.unisannio.catman.domain.humanresources.client;

import it.unisannio.catman.domain.humanresources.Worker;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Worker.class)
public interface WorkerProxy extends EntityProxy{
	String getName();
}
