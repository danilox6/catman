package it.unisannio.catman.domain.equipment.client;


import java.util.List;

import it.unisannio.catman.domain.equipment.Seller;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Seller.class)
public interface SellerProxy extends SupplierProxy {
	List<OfferProxy> getSupply();
	String getName();
	void setName(String name);
}
