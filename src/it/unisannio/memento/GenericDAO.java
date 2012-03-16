package it.unisannio.memento;

public interface GenericDAO<K, T> {
	public T find(K key);
	
	public T[] findAll(K... keys);
	
	public int count();
	
	public void delete(K... keys);
	
	public void save(T... obj);
	
	public T[] list(int offset, int size);
}
