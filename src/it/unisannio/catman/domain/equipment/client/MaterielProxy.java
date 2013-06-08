package it.unisannio.catman.domain.equipment.client;

import it.unisannio.catman.domain.equipment.Materiel;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Materiel.class)
public interface MaterielProxy extends EntityProxy {
	String getName();
	void setName(String name);
	boolean isConsumable();
	void setConsumable(boolean c); 
	String getDescription();
	void setDescription(String description);
}
