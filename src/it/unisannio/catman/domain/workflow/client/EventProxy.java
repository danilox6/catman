package it.unisannio.catman.domain.workflow.client;

import it.unisannio.catman.domain.workflow.Event;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Event.class)
public interface EventProxy extends EntityProxy {
	long getId();
	String getTitle();
	void setTitle(String title);
}
