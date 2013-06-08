package it.unisannio.catman.domain.equipment.client;

import it.unisannio.catman.domain.equipment.SupplyKey;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;

@ProxyFor(SupplyKey.class)
public interface SupplyKeyProxy extends ValueProxy {
	long getMaterielId();
	long getSupplierId();
}
