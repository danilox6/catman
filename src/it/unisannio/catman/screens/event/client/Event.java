package it.unisannio.catman.screens.event.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.IsWidget;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Screen;
import it.unisannio.catman.common.client.Screen.HasDetail;
import it.unisannio.catman.common.client.Screen.HasMaster;
import it.unisannio.catman.domain.planning.client.PlanProxy;
import it.unisannio.catman.domain.workflow.client.EventProxy;
import it.unisannio.catman.screens.event.client.queries.EventDocumentQuery;

public class Event extends Screen implements HasMaster, HasDetail{
	
	public static interface View extends IsWidget {
		void refresh();
		void setEventProxy(EventProxy eventProxy);
		void setDocumentQuery(EventDocumentQuery edq);
		void setPresenter(Presenter p);
	}
	
	public static interface Presenter {
		void goToPlan(PlanProxy pp);
		void addPlan();
		void setView(View v);
	}
	
	
	protected Event() {
		super("Event", "event", Icon.NOTES);
	}

	@Override
	public Activity getMaster(Intent i) {
		return new MasterActivity();
	}

	@Override
	public Activity getDetail(Intent i) {
		return new DetailActivity();
	}
}
