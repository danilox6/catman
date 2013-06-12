package it.unisannio.catman.domain.planning.client;

import it.unisannio.catman.domain.planning.Requirement;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Requirement.class)
public interface RequirementProxy extends EntityProxy {
	String getDescription();
	
	int getQuantity();
	void setQuantity(int q);

	void setPlan(PlanProxy plan);
	PlanProxy getPlan();
}
