package it.unisannio.memento.gwt;

import it.unisannio.memento.AbstractDAO;


public interface LocatorDAO<K, E> extends AbstractDAO<K, E> {
	E create(Class<? extends E> entityClass);
	
	K getId(E entity);
	
	E find(Class<? extends E> entityClass, K id);
	
	Object getVersion(E entity);
}
