package it.unisannio.catman.domain.planning.client;

import java.util.List;

import it.unisannio.catman.domain.humanresources.client.ContractProxy;
import it.unisannio.catman.domain.planning.Position;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Position.class)
public interface PositionProxy extends EntityProxy {
	List<ContractProxy> getFillers();
}
