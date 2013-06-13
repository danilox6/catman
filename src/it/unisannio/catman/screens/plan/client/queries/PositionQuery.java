package it.unisannio.catman.screens.plan.client.queries;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.IdentifiableAbstractQuery;
import it.unisannio.catman.domain.planning.client.PlanProxy;
import it.unisannio.catman.domain.planning.client.PositionProxy;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;

public class PositionQuery extends IdentifiableAbstractQuery<PositionProxy, Long> {
	
	private PlanProxy plan;
	
	public PositionQuery(PlanProxy plan) {
		this.plan = plan;
	}

	@Override
	public Request<List<PositionProxy>> list(int start, int length) {
		return App.getInstance().getDataStore().positions().listByPlan(plan, start, length).with("qualification");
	}

	@Override
	public Request<Integer> count() {
		return App.getInstance().getDataStore().positions().countByPlan(plan);
	}

	@Override
	protected Long getId(PositionProxy object) {
		return object.getId();
	}

	@Override
	protected Request<Void> deleteAllWithId(List<Long> ids) {
		return App.getInstance().getDataStore().positions().deleteByPlan(plan, ids);
	}

	@Override
	protected Request<Void> deleteSetWithId(List<Long> ids) {
		return App.getInstance().getDataStore().positions().delete(ids);
	}

	

}