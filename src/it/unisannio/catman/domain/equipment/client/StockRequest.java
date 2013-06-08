package it.unisannio.catman.domain.equipment.client;

import java.util.List;

import it.unisannio.catman.domain.equipment.Stock;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;

import com.google.web.bindery.requestfactory.shared.Service;

@Service(Stock.class)
public interface StockRequest extends RequestContext{
	Request<List<StockProxy>> listStocksByWarehouse(WarehouseProxy warehouse, int offset, int size);
	InstanceRequest<StockProxy, Void> persist();
}
