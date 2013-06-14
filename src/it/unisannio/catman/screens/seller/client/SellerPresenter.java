package it.unisannio.catman.screens.seller.client;

import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.ErrorHandler;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.ScreenActivity;
import it.unisannio.catman.domain.equipment.client.OfferProxy;
import it.unisannio.catman.domain.equipment.client.SellerProxy;
import it.unisannio.catman.screens.seller.client.queries.OfferQuery;

public class SellerPresenter implements Seller.Presenter{
	private Seller.View view;
	private ScreenActivity screenActivity;
	private SellerProxy seller;
	
	public SellerPresenter(Seller.View v, ScreenActivity activity, Intent intent) {
		this.view = v;
		this.screenActivity = activity;
		
		final DataStore dataStore = App.getInstance().getDataStore();
		try{
			EntityProxyId<SellerProxy> entityId = dataStore.getProxyId(intent.get(0, ""));
			dataStore.sellers().find(entityId).fire(new Receiver<SellerProxy>() {

				@Override
				public void onSuccess(final SellerProxy seller) {
					SellerPresenter.this.seller = seller;
					view.setSellerProxy(seller);
					view.setOfferProxyQuery(new OfferQuery(seller,null));
				}

				@Override
				public void onFailure(ServerFailure error) {
					ErrorHandler.handle(error.getMessage()); 
				}
			});
		}catch(IllegalArgumentException e){
			ErrorHandler.handle(); 
		}
	}

	@Override
	public void goToOfferScreen(OfferProxy m) {
		String id = App.getInstance().getDataStore().getHistoryToken(m.stableId());
		screenActivity.goTo(new Intent("offer").withParams(id));
	}

	@Override
	public void executeSearch(String searchQuery) {
		view.setOfferProxyQuery(new OfferQuery(seller,searchQuery));
	}

}
