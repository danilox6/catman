package it.unisannio.catman.domain.humanresources.client;

import java.util.Date;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

import it.unisannio.catman.domain.humanresources.EmploymentContract;

@ProxyFor(EmploymentContract.class)
public interface EmploymentContractProxy extends ContractProxy {

	public void setStartDate(Date s);
	public void setEndDate(Date e);
}
