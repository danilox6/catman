package it.unisannio.catman.domain.humanresources.client;

import it.unisannio.catman.domain.humanresources.Qualification;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Qualification.class)
public interface QualificationProxy extends EntityProxy{
	String getName();
	void setName(String n);
}
