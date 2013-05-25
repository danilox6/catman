package it.unisannio.catman.domain.equipment.client;

import it.unisannio.catman.domain.equipment.Supplier;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Supplier.class)
@ExtraTypes({OfferProxy.class,StockProxy.class})
public interface SupplierProxy<T extends SupplyProxy> extends EntityProxy {

	String getName();
	List<T> getSupply();
}
