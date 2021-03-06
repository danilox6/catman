package it.unisannio.catman.domain.equipment.client;

import it.unisannio.catman.domain.equipment.Supply;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ExtraTypes;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Supply.class)
@ExtraTypes({SupplyKeyProxy.class,SupplierProxy.class})
public interface SupplyProxy extends EntityProxy {
	SupplyKeyProxy getId();
	
	int getQuantity();
	void setQuantity(int quantity);
	
	MaterielProxy getMateriel();
	void setMateriel(MaterielProxy materiel);
	
	//FIXME togliere setter e getter Supplier da Stock e Offer e metterli qui	
//	SupplierProxy getSupplier();
//	void setSupplier(SupplierProxy supplier);
	
	 
}
