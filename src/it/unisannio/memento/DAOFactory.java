package it.unisannio.memento;

public interface DAOFactory {
	public <D extends GenericDAO<K, T>, K, T> D create(Class<D> clazz);
}
