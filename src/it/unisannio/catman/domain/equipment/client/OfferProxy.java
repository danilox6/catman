package it.unisannio.catman.domain.equipment.client;

import it.unisannio.catman.domain.equipment.Offer;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Offer.class)
public interface OfferProxy extends SupplyProxy{
	float getPrice();
}
