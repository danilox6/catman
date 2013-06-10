package it.unisannio.catman.screens.seller.client;

import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.google.web.bindery.requestfactory.shared.Receiver;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.ErrorHandler;
import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.common.client.ScreenActivity;
import it.unisannio.catman.domain.equipment.client.OfferProxy;
import it.unisannio.catman.domain.equipment.client.SellerProxy;

public class DetailActivity extends ScreenActivity implements Seller.Detail{

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		final Seller.Detail.View detailView = new DetailView();
		panel.setWidget(detailView);

		final DataStore dataStore = App.getInstance().getDataStore();
		try{
			EntityProxyId<SellerProxy> entityId = dataStore.getProxyId(getIntent().get(0, ""));
			
			dataStore.sellers().find(entityId).fire(new Receiver<SellerProxy>() {

				@Override
				public void onSuccess(final SellerProxy seller) {
					detailView.setSellerProxy(seller);
					Query<OfferProxy> query = new Query<OfferProxy>() {
						
						@Override
						public Request<List<OfferProxy>> list(int start, int length) {
							return dataStore.offers().listBySeller(seller, start, length).with("materiel.name","materiel.description");
						}
						
						@Override
						public Request<Integer> count() {
							return dataStore.offers().countBySeller(seller);
						}
						
						@Override
						public Request<Void> deleteSet(List<OfferProxy> set) {
							throw new UnsupportedOperationException(); //FIXME
						}
						
						@Override
						public Request<Void> deleteAll(List<OfferProxy> skip) {
							throw new UnsupportedOperationException(); //FIXME
						}
						
					};
					
					detailView.setOfferProxyQuery(query);
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

}
