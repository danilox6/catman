package it.unisannio.catman.screens.seller.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.IsWidget;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.common.client.Screen;
import it.unisannio.catman.common.client.Screen.HasDetail;
import it.unisannio.catman.common.client.Screen.HasMaster;
import it.unisannio.catman.domain.equipment.client.OfferProxy;
import it.unisannio.catman.domain.equipment.client.SellerProxy;

public class Seller extends Screen implements HasDetail, HasMaster{
	public static interface Detail extends Activity {
		interface View  extends IsWidget{
			void setSellerProxy(SellerProxy sellerProxy);
			void setOfferProxyQuery(Query<OfferProxy> query);
		}
	}
	public static interface Master extends Activity {
		interface View extends IsWidget{
			void setSellerProxy(SellerProxy sellerProxy);
			void setOfferProxyQuery(Query<OfferProxy> query);
		}
	}

	protected Seller() {
		super("Seller", "seller", Icon.SHOPPING_CART);
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
