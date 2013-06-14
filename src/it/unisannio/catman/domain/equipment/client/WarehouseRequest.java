package it.unisannio.catman.domain.equipment.client;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

import it.unisannio.catman.common.client.HasFindAll;
import it.unisannio.catman.domain.equipment.Warehouse;

@Service(Warehouse.class)
public interface WarehouseRequest extends RequestContext, HasFindAll<WarehouseProxy> {
	InstanceRequest<WarehouseProxy, Void> persist();
	InstanceRequest<WarehouseProxy, Void> addSupply(StockProxy proxy);
	
	Request<List<WarehouseProxy>> findAll();
	Request<List<WarehouseProxy>> findMoveableFrom(StockProxy supply);
}
