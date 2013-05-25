package it.unisannio.catman.domain.equipment.client;

import it.unisannio.catman.domain.equipment.Supplier;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.ExtraTypes;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(Supplier.class)
@ExtraTypes({WarehouseProxy.class, SellerProxy.class})
public interface SupplierRequest extends RequestContext {
	
	Request<List<SupplierProxy>> listAll(int start, int length);
	Request<Integer> count();
}
