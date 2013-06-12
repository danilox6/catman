package it.unisannio.catman.screens.plan.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

import it.unisannio.catman.common.client.ScreenActivity;
import it.unisannio.catman.domain.planning.client.PlanProxy;
import it.unisannio.catman.domain.planning.client.PositionProxy;
import it.unisannio.catman.domain.planning.client.ProcurementProxy;
import it.unisannio.catman.screens.plan.client.Plan.Presenter;
import it.unisannio.catman.screens.plan.client.Plan.View;
import it.unisannio.catman.screens.plan.client.queries.PositionQuery;
import it.unisannio.catman.screens.plan.client.queries.ProcurementQuery;

public class PlanPresenter implements Presenter {
	
	private View view;
	private PlanProxy plan;
	
	private ScreenActivity owner;

	public PlanPresenter(ScreenActivity owner, PlanProxy plan) {
		this.plan = plan;
		this.owner = owner;
	}

	@Override
	public void setView(View v) {
		this.view = v;
		view.setPositionQuery(new PositionQuery(plan));
		view.setProcurementQuery(new ProcurementQuery(plan));
	}

	@Override
	public void goTo(PositionProxy pp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goTo(ProcurementProxy pp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPosition() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addProcurement() {
		ProcurementEditor pe = new ProcurementEditor(plan);
		pe.open();
		pe.addValueChangeHandler(new ValueChangeHandler<ProcurementProxy>() {

			@Override
			public void onValueChange(ValueChangeEvent<ProcurementProxy> event) {
				view.refreshProcurements();
				
			}
			
		});
		
	}

	@Override
	public void editPosition(PositionProxy pp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editProcurement(ProcurementProxy pp) {
		ProcurementEditor pe = new ProcurementEditor(plan);
		pe.open(pp);
		pe.addValueChangeHandler(new ValueChangeHandler<ProcurementProxy>() {

			@Override
			public void onValueChange(ValueChangeEvent<ProcurementProxy> event) {
				view.refreshProcurements();
				
			}
			
		});
		
	}

}
