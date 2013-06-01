package it.unisannio.catman.domain.equipment.client;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

import it.unisannio.catman.domain.equipment.Warehouse;

@Service(Warehouse.class)
public interface WarehouseRequest extends RequestContext {
	InstanceRequest<WarehouseProxy, Void> persist();
}
