package it.unisannio.catman.domain.planning.client;

import java.util.List;

import it.unisannio.catman.domain.documents.client.DocumentProxy;
import it.unisannio.catman.domain.planning.Plan;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Plan.class)
public interface PlanProxy extends DocumentProxy {
	List<ProcurementProxy> getProcurements();
	List<PositionProxy> getPositions();
	
}
