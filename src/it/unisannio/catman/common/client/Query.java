package it.unisannio.catman.common.client;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.Request;

public interface Query<E extends EntityProxy> {
	Request<List<E>> list(int start, int length);
	Request<Integer> count();
	Request<Void> deleteAll(List<E> skip);
	Request<Void> deleteSet(List<E> set);
}
