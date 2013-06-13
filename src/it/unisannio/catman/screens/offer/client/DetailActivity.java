package it.unisannio.catman.screens.offer.client;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.LoadingScreenActivity;
import it.unisannio.catman.domain.equipment.client.OfferProxy;
import it.unisannio.catman.domain.equipment.client.OfferRequest;

public class DetailActivity extends LoadingScreenActivity<OfferRequest, OfferProxy, DetailView> {

	public DetailActivity() {
		super(App.getInstance().getDataStore().offers());
	}

	@Override
	protected DetailView onViewSetup() {
		return new DetailView();
	}

	@Override
	protected void onLoad(OfferProxy object) {
		// TODO Auto-generated method stub
		
	}

}
