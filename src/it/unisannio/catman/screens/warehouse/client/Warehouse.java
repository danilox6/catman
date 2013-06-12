package it.unisannio.catman.screens.warehouse.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.IsWidget;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.common.client.Screen;
import it.unisannio.catman.common.client.Screen.HasDetail;
import it.unisannio.catman.common.client.Screen.HasMaster;
import it.unisannio.catman.domain.equipment.client.MaterielProxy;
import it.unisannio.catman.domain.equipment.client.StockProxy;
import it.unisannio.catman.domain.equipment.client.WarehouseProxy;

public class Warehouse extends Screen implements HasDetail, HasMaster{
	
	public static interface View extends IsWidget{
			void setWarehouseProxy(WarehouseProxy warehouseProxy);
			void setStockProxyQuery(Query<StockProxy> query);
			void setPresenter(Presenter presenter);
	}
	
	public static interface Presenter{
		void goToMaterielScreen(MaterielProxy m);
	}

	protected Warehouse() {
		super("Warehouse", "warehouse", Icon.FORKLIFT); 
	}

	@Override
	public Activity getDetail(Intent i) {
		return new DetailActivity();
	}
	
	@Override
	public Activity getMaster(Intent i) {
		return new MasterActivity();
	}
	
}
