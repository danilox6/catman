package it.unisannio.catman.domain.workflow.client;

import java.util.List;

import it.unisannio.catman.domain.planning.client.PlanProxy;
import it.unisannio.catman.domain.workflow.EventDocument;

import com.google.web.bindery.requestfactory.shared.ExtraTypes;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(EventDocument.class)
@ExtraTypes({PlanProxy.class})
public interface EventDocumentRequest extends RequestContext {
	Request<List<EventDocumentProxy>> listByEvent(EventProxy ep, int start, int length);
	Request<Integer> countByEvent(EventProxy ep);
}
