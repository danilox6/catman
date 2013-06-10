package it.unisannio.catman.domain.equipment.client;

import it.unisannio.catman.domain.equipment.Seller;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(Seller.class)
public interface SellerRequest extends RequestContext{
	InstanceRequest<SellerProxy, Void> persist();
}
