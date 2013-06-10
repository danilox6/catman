package it.unisannio.catman.domain.equipment.client;

import it.unisannio.catman.domain.equipment.Offer;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Offer.class)
public interface OfferProxy extends SupplyProxy{

	void setPrice(float price);
	float getPrice();
	void setSupplier(SellerProxy seller);
	public SellerProxy getSupplier();
}
