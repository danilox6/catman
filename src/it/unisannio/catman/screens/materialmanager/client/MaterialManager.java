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
	public static interface Master extends Activity {
		
		void goToSupplyScreen(SupplierProxy s);
		
		interface View extends IsWidget{
			void setSupplierQuery(Query<SupplierProxy> query);
		}
	}

	protected MaterialManager() {
		super("Materials", "material-manager", Icon.PACKAGE);
	}

	@Override
	public Activity getMaster(Intent i) {
		return new MasterActivity();
	}

}
