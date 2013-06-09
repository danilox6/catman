package it.unisannio.catman.domain.workflow.client;

import it.unisannio.catman.domain.workflow.EventDocument;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(EventDocument.class)
public interface EventDocumentProxy extends EntityProxy {
	boolean isComplete();
	String getTitle();
}
