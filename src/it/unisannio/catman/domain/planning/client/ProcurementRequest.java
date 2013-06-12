package it.unisannio.catman.domain.planning.client;

import java.util.List;

import it.unisannio.catman.domain.planning.Procurement;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(Procurement.class)
public interface ProcurementRequest extends RequestContext {
	InstanceRequest<ProcurementProxy, Void> persist();
	Request<List<ProcurementProxy>> findAll();
	Request<List<ProcurementProxy>> listByPlan(PlanProxy pp, int start, int len);
	Request<Integer> countByPlan(PlanProxy pp);
}
