package it.unisannio.catman.screens.plan.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.IsWidget;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Screen;
import it.unisannio.catman.common.client.Screen.HasMaster;
import it.unisannio.catman.common.client.Screen.HasDetail;
import it.unisannio.catman.domain.planning.client.PositionProxy;
import it.unisannio.catman.domain.planning.client.ProcurementProxy;
import it.unisannio.catman.screens.plan.client.queries.PositionQuery;
import it.unisannio.catman.screens.plan.client.queries.ProcurementQuery;


public class Plan extends Screen  implements HasMaster, HasDetail{
	interface Presenter {
		void setView(View v);
		void goTo(PositionProxy pp);
		void goTo(ProcurementProxy pp);
		void addPosition();
		void addProcurement();
		void editPosition(PositionProxy pp);
		void editProcurement(ProcurementProxy pp);
		
	}
	
	interface View extends IsWidget {
		static final String EDIT_ATTRIBUTE = "data-editor";
		
		void setPresenter(Presenter p);
		void setProcurementQuery(ProcurementQuery pq);
		void setPositionQuery(PositionQuery pq);
		void refreshProcurements();
		void refreshPositions();
	}
	
	
	public Plan() {
		super("Plan", "plan", Icon.CHECKLIST); 
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
