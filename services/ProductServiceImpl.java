package services;

import java.util.List;

import repository.GenericDAO;
import repository.sql.ProductRepoImpl;

import domains.Product;

public class ProductServiceImpl implements ProductService {

	private GenericDAO<Product> dao=new ProductRepoImpl();
	
	@Override
	public Product create(Product obj) {
		// TODO Auto-generated method stub
		return dao.create(obj);
	}

	@Override
	public Product find(long id) {
		// TODO Auto-generated method stub
		return dao.find(id);
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Product update(Product obj) {
		// TODO Auto-generated method stub
		return dao.update(obj);
	}

}
