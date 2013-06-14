package it.unisannio.catman.screens.seller.client.queries;

import it.unisannio.catman.common.client.AbstractQuery;
import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.domain.equipment.client.OfferProxy;
import it.unisannio.catman.domain.equipment.client.SellerProxy;

import java.util.List;


import com.google.web.bindery.requestfactory.shared.Request;

public class OfferQuery extends AbstractQuery<OfferProxy> {
	private static final DataStore dataStore = App.getInstance().getDataStore();
	
	private SellerProxy seller;
	private String searchQuery;
	
	public OfferQuery(SellerProxy seller, String searchQuery) {
		this.seller = seller;
		this.searchQuery = searchQuery;
	}

	@Override
	public Request<List<OfferProxy>> list(int start, int length) {
		return dataStore.offers().listBySeller(seller,searchQuery, start, length).with("materiel.name","materiel.description");
	}

	@Override
	public Request<Integer> count() {
		return dataStore.offers().countBySeller(seller, searchQuery);
	}


}
