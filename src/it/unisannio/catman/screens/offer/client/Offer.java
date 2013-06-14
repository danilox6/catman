package it.unisannio.catman.screens.offer.client;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Screen;
import it.unisannio.catman.common.client.Screen.HasDetail;
import it.unisannio.catman.domain.equipment.client.OfferProxy;
import it.unisannio.catman.domain.equipment.client.WarehouseProxy;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.IsWidget;

public class Offer extends Screen implements HasDetail{
	public static interface Presenter {
		void buy(OfferProxy stock, WarehouseProxy destination, int quantity);
		void delete(OfferProxy stock);
	}
	
	public static interface View extends IsWidget {
		void setPresenter(Presenter p);
		void setOffer(OfferProxy o);
		void setQuantity(int quantity);
	}

	protected Offer() {
		super("offer","offer", Icon.BREAKABLE);
	}

	@Override
	public Activity getDetail(Intent i) {
		return new DetailActivity();
	}

}