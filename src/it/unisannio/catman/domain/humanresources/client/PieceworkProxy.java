package it.unisannio.catman.domain.humanresources.client;

import it.unisannio.catman.domain.humanresources.Piecework;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Piecework.class)
public interface PieceworkProxy extends EntityProxy {

	QualificationProxy getQualification();
	void setQualification(QualificationProxy q);
	

	float getPay();
	void setPay(float w);

}
