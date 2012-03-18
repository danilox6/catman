package it.unisannio.memento;

public interface DAOFactory {
	public <D extends GenericDAO<K, T>, K, T> D getInstance(Class<D> daoClass);
	
	public <D extends GenericDAO<K, T>, K, T> D getDaoFor(Class<? extends CustomDAO<D>> entityClass);
	
	public <T> GenericDAO<?, T> getGenericDaoFor(Class<T> entityClass);

}
