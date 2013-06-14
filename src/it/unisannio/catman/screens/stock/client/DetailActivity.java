package it.unisannio.catman.screens.stock.client;

import com.google.web.bindery.requestfactory.shared.Receiver;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.LoadingScreenActivity;
import it.unisannio.catman.domain.equipment.client.StockProxy;
import it.unisannio.catman.domain.equipment.client.StockRequest;
import it.unisannio.catman.domain.equipment.client.WarehouseProxy;
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

	@Override
	public void move(StockProxy stock, WarehouseProxy destination, int quantity) {
		App.getInstance().getDataStore().stocks()
			.move(quantity, destination)
			.using(stock)
			.fire(new Receiver<Integer>() {
				@Override
				public void onSuccess(Integer response) {
					getView().setQuantity(response);
					// TODO Trigger global refresh here
				}
				
			});
	}

	@Override
	public void delete(StockProxy stock) {
		App.getInstance().getDataStore().stocks().remove().using(stock).fire(new Receiver<Void>() {

			@Override
			public void onSuccess(Void response) {
				goUp();				
			}
			
		});
		
	}

}
