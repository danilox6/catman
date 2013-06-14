package it.unisannio.catman.domain.equipment.client;

import java.util.List;

import it.unisannio.catman.domain.equipment.Offer;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(Offer.class)
public interface OfferRequest extends RequestContext {
	InstanceRequest<OfferProxy, Void> persist();
	Request<List<OfferProxy>> listBySeller(SellerProxy seller, int start, int length);
	Request<Integer> countBySeller(SellerProxy seller);
	
	Request<List<OfferProxy>> findByMateriel(MaterielProxy m);
	Request<Integer> countByMateriel(MaterielProxy warehouse);
	
}
