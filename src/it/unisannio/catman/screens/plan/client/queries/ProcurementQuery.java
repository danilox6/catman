package it.unisannio.catman.screens.plan.client.queries;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.domain.planning.client.PlanProxy;
import it.unisannio.catman.domain.planning.client.ProcurementProxy;

public class ProcurementQuery implements Query<ProcurementProxy> {
	
	private PlanProxy plan;
	
	public ProcurementQuery(PlanProxy plan) {
		this.plan = plan;
	}

	@Override
	public Request<List<ProcurementProxy>> list(int start, int length) {
		return App.getInstance().getDataStore().procurements().listByPlan(plan, start, length);
	}

	@Override
	public Request<Integer> count() {
		return App.getInstance().getDataStore().procurements().countByPlan(plan);
	}

	@Override
	public Request<Void> deleteAll(List<ProcurementProxy> skip) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Request<Void> deleteSet(List<ProcurementProxy> set) {
		throw new UnsupportedOperationException();
	}

}
