package it.unisannio.catman.common.client;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.Request;

public interface HasFindAll<E extends EntityProxy> {
	Request<List<E>> findAll();

}
