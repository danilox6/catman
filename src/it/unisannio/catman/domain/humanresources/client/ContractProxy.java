package it.unisannio.catman.domain.humanresources.client;

import java.util.Date;

import it.unisannio.catman.domain.humanresources.Contract;
import it.unisannio.catman.domain.workflow.client.EventProxy;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Contract.class)
public interface ContractProxy extends EntityProxy {
	QualificationProxy getQualification();
	void setQualification(QualificationProxy q);
	
	EventProxy getEvent();
	void setEvent(EventProxy e);
	
	float getWage();
	void setWage(float w);
	
	Date getStartDate();
	void setStartDate(Date d);
	
	Date getEndDate();
	void setEndDate(Date d);
	
	boolean isOpenEnded();
	boolean isFreelance();
}
