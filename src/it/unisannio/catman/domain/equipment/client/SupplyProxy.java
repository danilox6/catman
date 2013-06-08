package it.unisannio.catman.domain.equipment.client;

import it.unisannio.catman.domain.equipment.Supply;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Supply.class)
public interface SupplyProxy extends EntityProxy {
	 int getQuantity();
	 void setQuantity(int quantity);
	 MaterielProxy getMateriel();
	 void setMateriel(MaterielProxy materiel);
	 
}
