package it.unisannio.catman.domain.equipment.client;

import java.util.List;

import it.unisannio.catman.domain.equipment.Stock;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;

import com.google.web.bindery.requestfactory.shared.Service;

@Service(Stock.class)
public interface StockRequest extends RequestContext{
	Request<List<StockProxy>> listByWarehouse(WarehouseProxy warehouse, int start, int length);
	InstanceRequest<StockProxy, Void> persist();
	Request<List<StockProxy>> listAll(int start, int length);
	Request<Integer> count();
	Request<Integer> countByWarehouse(WarehouseProxy warehouse);
	Request<List<StockProxy>> findByMateriel(MaterielProxy m);
	Request<Integer> countByMateriel(MaterielProxy warehouse);
}
