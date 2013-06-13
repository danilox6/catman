package it.unisannio.catman.screens.stock.client;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.LoadingScreenActivity;
import it.unisannio.catman.domain.equipment.client.StockProxy;
import it.unisannio.catman.domain.equipment.client.StockRequest;
import it.unisannio.catman.screens.stock.client.Stock.Presenter;
import it.unisannio.catman.screens.stock.client.Stock.View;

public class DetailActivity extends LoadingScreenActivity<StockRequest, StockProxy, DetailView> implements Presenter {

	public DetailActivity() {
		super(App.getInstance().getDataStore().stocks(), "materiel");
	}

	@Override
	protected DetailView onViewSetup() {
		return new DetailView();
	}

	@Override
	protected void onLoad(StockProxy object) {
		View v = getView();
		v.setPresenter(this);
		v.setStock(object);
		
	}

}
