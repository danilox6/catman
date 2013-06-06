package it.unisannio.catman.domain.workflow.client;

import java.util.Date;

import it.unisannio.catman.domain.workflow.Event;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Event.class)
public interface EventProxy extends EntityProxy {

	String getTitle();
	void setTitle(String title);
	
	Date getStartDate();
	void setStartDate(Date d);
	
	Date getEndDate();
	void setEndDate(Date d);
}
