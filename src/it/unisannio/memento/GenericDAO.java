package it.unisannio.memento;

import java.util.List;

public interface GenericDAO<K, T> {
	public T find(K key);
	
	public List<T> findAll();
	
	public long count();
	
	public void delete(T obj);
	
	public void delete(List<T> obj);
	
	public void insert(T obj);
	
	public List<T> list(int offset, int size);
	
}
