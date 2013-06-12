package it.unisannio.catman.screens.materialmanager.client.queries;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.domain.equipment.client.SupplierProxy;

@SuppressWarnings("rawtypes")
public class SupplierQuery implements Query<SupplierProxy>{
	private static final DataStore dataStore = App.getInstance().getDataStore();
	
	@Override
	public Request<List<SupplierProxy>> list(int start, int length) {
		return dataStore.suppliers().listAll(start, length);
	}

	@Override
	public Request<Integer> count() {
		return dataStore.suppliers().count();
	}

	@Override
	public Request<Void> deleteAll(List<SupplierProxy> skip) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Request<Void> deleteSet(List<SupplierProxy> set) {
		throw new UnsupportedOperationException();
	}

}
