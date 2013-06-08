package it.unisannio.catman.domain.equipment.client;

import it.unisannio.catman.domain.equipment.Materiel;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(Materiel.class)
public interface MaterielRequest extends RequestContext{
	InstanceRequest<MaterielProxy, Void> persist();
	Request<Integer> count();
}
