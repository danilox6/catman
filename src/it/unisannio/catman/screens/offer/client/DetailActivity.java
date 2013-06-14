package it.unisannio.catman.screens.offer.client;

import com.google.web.bindery.requestfactory.shared.Receiver;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.LoadingScreenActivity;
import it.unisannio.catman.domain.equipment.client.OfferProxy;
import it.unisannio.catman.domain.equipment.client.OfferRequest;
import it.unisannio.catman.domain.equipment.client.StockProxy;
import it.unisannio.catman.domain.equipment.client.WarehouseProxy;
import it.unisannio.catman.screens.offer.client.Offer.Presenter;
import it.unisannio.catman.screens.offer.client.Offer.View;

public class DetailActivity extends LoadingScreenActivity<OfferRequest, OfferProxy, DetailView> implements Presenter {

	public DetailActivity() {
		super(App.getInstance().getDataStore().offers(), "materiel");
	}

	@Override
	protected DetailView onViewSetup() {
		return new DetailView();
	}

	@Override
	protected void onLoad(OfferProxy object) {
		View v = getView();
		v.setPresenter(this);
		v.setOffer(object);
		
	}

	@Override
	public void buy(OfferProxy offer, WarehouseProxy destination, int quantity) {
		App.getInstance().getDataStore().offers()
			.buy(quantity, destination)
			.using(offer)
			.fire(new Receiver<Integer>() {
				@Override
				public void onSuccess(Integer response) {
					getView().setQuantity(response);
					// TODO Trigger global refresh here
				}
				
			});
	}

	@Override
	public void delete(OfferProxy offer) {
		App.getInstance().getDataStore().offers().remove().using(offer).fire(new Receiver<Void>() {

			@Override
			public void onSuccess(Void response) {
				goUp();				
			}
			
		});
		
	}
}
