package it.unisannio.catman.domain.workflow.client;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

import it.unisannio.catman.domain.workflow.Event;
import it.unisannio.catman.domain.workflow.client.EventProxy;

@Service(Event.class)
public interface EventRequest extends RequestContext {
	Request<List<EventProxy>> findAll();
}
