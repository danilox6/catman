package it.unisannio.catman.domain.planning.client;

import it.unisannio.catman.domain.planning.Plan;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;


@Service(Plan.class)
public interface PlanRequest extends RequestContext {
	InstanceRequest<PlanProxy, Void> persist();
}
