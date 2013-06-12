package it.unisannio.catman.domain.planning.client;

import java.util.List;

import it.unisannio.catman.domain.planning.Plan;
import it.unisannio.catman.domain.workflow.client.EventDocumentProxy;
import it.unisannio.catman.domain.workflow.client.EventProxy;

import com.google.web.bindery.requestfactory.shared.ExtraTypes;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Plan.class)
@ExtraTypes({ProcurementProxy.class, PositionProxy.class})
public interface PlanProxy extends EventDocumentProxy {
	List<ProcurementProxy> getProcurements();
	List<PositionProxy> getPositions();
	void setDossier(EventProxy ep);
}
