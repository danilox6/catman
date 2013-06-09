package it.unisannio.catman.screens.event.client;

import com.google.web.bindery.requestfactory.shared.Receiver;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.ScreenActivity;
import it.unisannio.catman.domain.planning.client.PlanProxy;
import it.unisannio.catman.domain.workflow.client.EventProxy;
import it.unisannio.catman.domain.workflow.client.EventRequest;
import it.unisannio.catman.screens.event.client.Event.Presenter;
import it.unisannio.catman.screens.event.client.Event.View;
import it.unisannio.catman.screens.event.client.queries.EventDocumentQuery;

public class EventPresenter implements Presenter {
	
	private View view;
	private EventProxy entity;
	private ScreenActivity owner;

	public EventPresenter(EventProxy entity, ScreenActivity owner) {
		this.entity = entity;
		this.owner = owner;
	}
	
	public void setView(View v) {
		this.view = v;
		view.setDocumentQuery(new EventDocumentQuery(entity));
		view.setEventProxy(entity);
	}

	@Override
	public void goToPlan(PlanProxy pp) {
		String id = App.getInstance().getDataStore().getHistoryToken(pp.stableId());
		owner.goTo(new Intent("plan").withParams(id));
		
	}

	@Override
	public void addPlan() {
		EventRequest pr = App.getInstance().getDataStore().events();
		pr.addPlan().using(entity).fire(new Receiver<PlanProxy>() {

			@Override
			public void onSuccess(PlanProxy response) {
				view.refresh();
			}
		});
		
	}

}
