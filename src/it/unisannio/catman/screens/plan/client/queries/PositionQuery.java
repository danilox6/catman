package it.unisannio.catman.screens.plan.client.queries;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.domain.planning.client.PlanProxy;
import it.unisannio.catman.domain.planning.client.PositionProxy;
import it.unisannio.catman.domain.planning.client.ProcurementProxy;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;

public class PositionQuery implements Query<PositionProxy> {
	
	private PlanProxy plan;
	
	public PositionQuery(PlanProxy plan) {
		this.plan = plan;
	}

	@Override
	public Request<List<PositionProxy>> list(int start, int length) {
		return App.getInstance().getDataStore().positions().listByPlan(plan, start, length);
	}

	@Override
	public Request<Integer> count() {
		return App.getInstance().getDataStore().positions().countByPlan(plan);
	}

	@Override
	public Request<Void> deleteAll(List<PositionProxy> skip) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Request<Void> deleteSet(List<PositionProxy> set) {
		throw new UnsupportedOperationException();
	}

}