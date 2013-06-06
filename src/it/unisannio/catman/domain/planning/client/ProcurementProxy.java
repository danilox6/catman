package it.unisannio.catman.domain.planning.client;

import java.util.Set;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

import it.unisannio.catman.domain.planning.Procurement;

@ProxyFor(Procurement.class)
public interface ProcurementProxy extends RequirementProxy {
	Set<SourceProxy> getSources();
}
