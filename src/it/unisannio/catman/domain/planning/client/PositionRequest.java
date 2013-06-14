package it.unisannio.catman.domain.planning.client;

import java.util.List;

import it.unisannio.catman.domain.humanresources.client.PieceworkProxy;
import it.unisannio.catman.domain.planning.Position;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(Position.class)
public interface PositionRequest extends RequestContext {
	Request<List<PositionProxy>> listByPlan(PlanProxy pp, int start, int length);
	Request<Integer> countByPlan(PlanProxy pp);
	Request<Void> delete(List<Long> keys);
	Request<Void> deleteByPlan(PlanProxy pp, List<Long> exclusions);
	
	Request<List<PositionProxy>> findByPiecework(PieceworkProxy piecework);
	
	InstanceRequest<PositionProxy, Void> persist();
}
