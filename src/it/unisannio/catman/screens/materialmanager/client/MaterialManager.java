package it.unisannio.catman.screens.materialmanager.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.IsWidget;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.common.client.Screen;
import it.unisannio.catman.common.client.Screen.HasMaster;
import it.unisannio.catman.domain.equipment.client.SupplierProxy;

@SuppressWarnings("rawtypes") 
public class MaterialManager extends Screen implements HasMaster{
	public static interface Presenter {
		void goToSupplyScreen(SupplierProxy s);
	}
	
	public static interface View extends IsWidget{
		void setSupplierQuery(Query<SupplierProxy> query);
		void setPresenter(Presenter presenter);
	}

	protected MaterialManager() {
		super("Materials", "material-manager", Icon.PACKAGE);
	}

	@Override
	public Activity getMaster(Intent i) {
		return new MasterActivity();
	}

}
