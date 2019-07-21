package repository;

import java.util.List;

public interface GenericDAO<T> 
{
		public T create(T obj); 
	
		public T find(long id);
		
		public List<T> findAll();
		
		public T update(T obj);		
		
		public T findByName(String name);

}
