package it.unisannio.catman.domain.equipment.client;

import java.util.List;

import it.unisannio.catman.common.client.HasFindAll;
import it.unisannio.catman.domain.equipment.Materiel;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(Materiel.class)
public interface MaterielRequest extends RequestContext, HasFindAll<MaterielProxy>{
	InstanceRequest<MaterielProxy, Void> persist();
	Request<Integer> count();
	Request<List<MaterielProxy>> findAll();
}
