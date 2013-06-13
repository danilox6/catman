package it.unisannio.catman.domain.equipment.client;

import java.util.List;

import it.unisannio.catman.domain.equipment.Supply;

import com.google.web.bindery.requestfactory.shared.ExtraTypes;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(Supply.class)
@ExtraTypes({OfferProxy.class,StockProxy.class})
public interface SupplyRequest extends RequestContext {
	
	Request<List<SupplyProxy>> listByMateriel(MaterielProxy materiel, int start, int length);
	Request<Integer> countByMateriel(MaterielProxy materiel);
}
