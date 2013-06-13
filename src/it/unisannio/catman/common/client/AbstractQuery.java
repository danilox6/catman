package it.unisannio.catman.common.client;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.Request;

public abstract class AbstractQuery<E extends EntityProxy> implements Query<E> {

	@Override
	public Request<Void> deleteAll(List<E> skip) {
		throw new UnsupportedOperationException("This query doesn't support deletion");
	}

	@Override
	public Request<Void> deleteSet(List<E> set) {
		throw new UnsupportedOperationException("This query doesn't support deletion");
	}

}
