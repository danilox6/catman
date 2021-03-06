package it.unisannio.catman.domain.planning.client;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

import it.unisannio.catman.domain.equipment.client.SupplyProxy;
import it.unisannio.catman.domain.planning.Source;

@ProxyFor(Source.class)
public interface SourceProxy extends EntityProxy {
	int getQuantity();
	void setQuantity(int q);
	
	SupplyProxy getSupply();
	void setSupply(SupplyProxy p);
}
