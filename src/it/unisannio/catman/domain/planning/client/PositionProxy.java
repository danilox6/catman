package it.unisannio.catman.domain.planning.client;

import java.util.List;

import it.unisannio.catman.domain.humanresources.client.ContractProxy;
import it.unisannio.catman.domain.humanresources.client.QualificationProxy;
import it.unisannio.catman.domain.planning.Position;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Position.class)
public interface PositionProxy extends RequirementProxy {
	List<ContractProxy> getFillers();
	
	QualificationProxy getQualification();
	void setQualification(QualificationProxy q);
	
	Long getId();
}
