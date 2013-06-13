package it.unisannio.catman.common.client;

import java.util.ArrayList;
import java.util.List;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.Request;

public abstract class IdentifiableAbstractQuery<E extends EntityProxy, K> extends AbstractQuery<E> {

	protected abstract K getId(E object);
	
	private List<K> accumulateIds(List<E> objects) {
		List<K> ids = new ArrayList<K>(objects.size());
		for(E e : objects) {
			ids.add(getId(e));
		}
		return ids;
	}
	
	@Override
	public Request<Void> deleteAll(List<E> skip) {
		return deleteAllWithId(accumulateIds(skip));
	}
	
	@Override
	public Request<Void> deleteSet(List<E> set) {
		return deleteSetWithId(accumulateIds(set));
	}
	
	protected abstract Request<Void> deleteAllWithId(List<K> ids);
	protected abstract Request<Void> deleteSetWithId(List<K> ids);

}
