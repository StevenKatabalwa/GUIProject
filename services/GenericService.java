package services;

import java.util.List;

public interface GenericService<T> {

	public T create(T obj); 
	
	public T find(long id);
	
	public List<T> findAll();
	
	public T update(T obj);		
	
}
