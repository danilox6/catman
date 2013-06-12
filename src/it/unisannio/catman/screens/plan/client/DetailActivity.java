package it.unisannio.catman.screens.plan.client;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.LoadingScreenActivity;
import it.unisannio.catman.domain.planning.client.PlanProxy;
import it.unisannio.catman.domain.planning.client.PlanRequest;
import it.unisannio.catman.screens.plan.client.Plan.Presenter;
import it.unisannio.catman.screens.plan.client.Plan.View;


public class DetailActivity extends LoadingScreenActivity<PlanRequest, PlanProxy, View> {

	public DetailActivity() {
		super(App.getInstance().getDataStore().plans());
	}

	@Override
	protected View onViewSetup() {
		return new DetailView();
	}

	@Override
	protected void onLoad(PlanProxy object) {
		Presenter presenter = new PlanPresenter(this, object);
		View view = getView();
		view.setPresenter(presenter);
		presenter.setView(view);
		
	}


}
