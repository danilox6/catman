package it.unisannio.catman.screens.materialpicker.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.ErrorHandler;
import it.unisannio.catman.common.client.ScreenActivity;
import it.unisannio.catman.domain.equipment.client.SupplyProxy;
import it.unisannio.catman.domain.planning.client.ProcurementProxy;
import it.unisannio.catman.screens.materialpicker.client.MaterialPicker.View;
import it.unisannio.catman.screens.materialpicker.client.queries.SupplyByMaterielQuery;

public class DetailActivity extends ScreenActivity implements MaterialPicker.Presenter {

	private ProcurementProxy procurement;
	View view;
	
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.view = new DetailView();
		panel.setWidget(view);
		
		fetchProcurementProxy();
	}
	

	@Override
	public void moveMateriel(SupplyProxy supply, int quantity, ProcurementProxy procurement) {
		getDataStore().procurements().move(quantity, supply).using(procurement).fire(new Receiver<Void>() {

			@Override
			public void onSuccess(Void response) {
				refresh();
			}
		});
	}

	@Override
	public void refresh() {
		fetchProcurementProxy();
		view.refresh();
	}

	private void fetchProcurementProxy(){
		try{
			DataStore ds = getDataStore();
			EntityProxyId<ProcurementProxy> entityId = ds.getProxyId(getIntent().get(0, ""));
			Request<ProcurementProxy> r = getDataStore().procurements().find(entityId);
			r.with("materiel","sources","sources.supply");
			r.fire(new Receiver<ProcurementProxy>() {

				@Override
				public void onSuccess(ProcurementProxy response) {
					procurement = response;
					view.setPresenter(DetailActivity.this);
					view.setProcurementProxy(procurement);
					view.setSupplyQuery(new SupplyByMaterielQuery(procurement.getMateriel()));
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
