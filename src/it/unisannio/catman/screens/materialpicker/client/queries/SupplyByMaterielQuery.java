package it.unisannio.catman.screens.materialpicker.client.queries;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;

import it.unisannio.catman.common.client.AbstractQuery;
import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.domain.equipment.client.MaterielProxy;
import it.unisannio.catman.domain.equipment.client.SupplyProxy;

public class SupplyByMaterielQuery extends AbstractQuery<SupplyProxy>{
	private static final DataStore dataStore = App.getInstance().getDataStore();

	private MaterielProxy materiel;
	
	public SupplyByMaterielQuery(MaterielProxy materiel) {
		this.materiel = materiel;
	}

	@Override
	public Request<List<SupplyProxy>> list(int start, int length) {
		return dataStore.supplies().listByMateriel(materiel, start, length).with("supplier");
	}

	@Override
	public Request<Integer> count() {
		return dataStore.supplies().countByMateriel(materiel);
	}


}
