package it.unisannio.catman.screens.supply.client;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.LoadingScreenActivity;
import it.unisannio.catman.domain.equipment.client.SupplyProxy;
import it.unisannio.catman.domain.equipment.client.SupplyRequest;

public class DetailActivity extends LoadingScreenActivity<SupplyRequest, SupplyProxy, DetailView>{

	public DetailActivity() {
		super(App.getInstance().getDataStore().supplies());
	}

	@Override
	protected DetailView onViewSetup() {
		return new DetailView();
	}

	@Override
	protected void onLoad(SupplyProxy object) {
		// TODO Auto-generated method stub
		
	}
}
