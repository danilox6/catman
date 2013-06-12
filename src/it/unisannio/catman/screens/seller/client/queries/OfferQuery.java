package it.unisannio.catman.screens.seller.client.queries;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.domain.equipment.client.OfferProxy;
import it.unisannio.catman.domain.equipment.client.SellerProxy;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;

public class OfferQuery implements Query<OfferProxy> {
	private static final DataStore dataStore = App.getInstance().getDataStore();
	
	private SellerProxy seller;
	
	public OfferQuery(SellerProxy seller) {
		this.seller = seller;
	}

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

}
