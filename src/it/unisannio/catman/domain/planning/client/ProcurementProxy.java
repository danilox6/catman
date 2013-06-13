package it.unisannio.catman.domain.planning.client;

import java.util.List;
import com.google.web.bindery.requestfactory.shared.ExtraTypes;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

import it.unisannio.catman.domain.equipment.client.MaterielProxy;
import it.unisannio.catman.domain.planning.Procurement;

@ProxyFor(Procurement.class)
@ExtraTypes({SourceProxy.class})
public interface ProcurementProxy extends RequirementProxy {
	List<SourceProxy> getSources();
	
	void setMateriel(MaterielProxy m);
	MaterielProxy getMateriel();

	Long getId();

}
