package it.unisannio.catman.screens.plan.client.queries;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.IdentifiableAbstractQuery;
import it.unisannio.catman.domain.planning.client.PlanProxy;
import it.unisannio.catman.domain.planning.client.ProcurementProxy;

public class ProcurementQuery extends IdentifiableAbstractQuery<ProcurementProxy, Long> {
	
	private PlanProxy plan;
	
	public ProcurementQuery(PlanProxy plan) {
		this.plan = plan;
	}

	@Override
	public Request<List<ProcurementProxy>> list(int start, int length) {
		return App.getInstance().getDataStore().procurements().listByPlan(plan, start, length).with("materiel");
	}

	@Override
	public Request<Integer> count() {
		return App.getInstance().getDataStore().procurements().countByPlan(plan);
	}

	@Override
	protected Long getId(ProcurementProxy object) {
		return object.getId();
	}

	@Override
	protected Request<Void> deleteAllWithId(List<Long> ids) {
		return App.getInstance().getDataStore().procurements().deleteByPlan(plan, ids);
	}

	@Override
	protected Request<Void> deleteSetWithId(List<Long> ids) {
		return App.getInstance().getDataStore().procurements().delete(ids);
	}


}
