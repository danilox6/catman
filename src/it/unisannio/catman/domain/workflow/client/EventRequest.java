package it.unisannio.catman.domain.workflow.client;

import java.util.Date;
import java.util.List;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

import it.unisannio.catman.domain.planning.client.PlanProxy;
import it.unisannio.catman.domain.workflow.Event;
import it.unisannio.catman.domain.workflow.client.EventProxy;

@Service(Event.class)
public interface EventRequest extends RequestContext {
	Request<List<EventProxy>> findAll();
	Request<List<EventProxy>> listAll(int start, int length);
	Request<Integer> count();
	InstanceRequest<EventProxy, Void> persist();
	InstanceRequest<EventProxy, PlanProxy> addPlan();
	Request<List<EventProxy>> listBy(String searchQuery, Date date, int start, int length);
	Request<Integer> countBy(String searchQuery, Date date);
} 
