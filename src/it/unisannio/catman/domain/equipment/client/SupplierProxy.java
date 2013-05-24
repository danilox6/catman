package it.unisannio.catman.domain.equipment.client;

import it.unisannio.catman.domain.equipment.Supplier;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ExtraTypes;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Supplier.class)
//@ExtraTypes({WarehouseProxy.class, SellerProxy.class})
public interface SupplierProxy extends EntityProxy {

	String getName();
}
