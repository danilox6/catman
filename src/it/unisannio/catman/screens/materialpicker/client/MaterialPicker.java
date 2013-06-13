package it.unisannio.catman.screens.materialpicker.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.IsWidget;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.common.client.Screen;
import it.unisannio.catman.common.client.Screen.HasDetail;
import it.unisannio.catman.domain.equipment.client.SupplyProxy;
import it.unisannio.catman.domain.planning.client.ProcurementProxy;

public class MaterialPicker extends Screen implements HasDetail{
	public static interface View extends IsWidget {
		void setPresenter(Presenter detailActivity);
		void setProcurementProxy(ProcurementProxy procurement);
		void setSupplyQuery(Query<SupplyProxy> query);
		void refresh();
	}
	public static interface Presenter{
		void moveMateriel(SupplyProxy supply, int quantity, ProcurementProxy procurement);
		void refresh();
	}

	protected MaterialPicker() {
		super("Materiel Picker","material-picker", Icon.BASKET);
	}

	@Override
	public Activity getDetail(Intent i) {
		return new DetailActivity();
	}

}
