package it.unisannio.catman.screens.stock.client;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Screen;
import it.unisannio.catman.common.client.Screen.HasDetail;
import it.unisannio.catman.domain.equipment.client.StockProxy;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.IsWidget;

public class Stock extends Screen implements HasDetail{
	public static interface Presenter {
		
	}
	
	public static interface View extends IsWidget {
		void setPresenter(Presenter p);
		void setStock(StockProxy st);
	}

	protected Stock() {
		super("Stock","stock", Icon.BREAKABLE);
	}

	@Override
	public Activity getDetail(Intent i) {
		return new DetailActivity();
	}

}
