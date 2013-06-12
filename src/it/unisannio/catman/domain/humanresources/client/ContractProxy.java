package it.unisannio.catman.domain.humanresources.client;

import java.util.Date;

import it.unisannio.catman.domain.humanresources.Contract;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ExtraTypes;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Contract.class)
@ExtraTypes({FreelanceContractProxy.class, EmploymentContractProxy.class})
public interface ContractProxy extends EntityProxy {
	
	PieceworkProxy getPiecework();
	void setPiecework(PieceworkProxy p);
	
	Date getStartDate();
	
	Date getEndDate();
}
