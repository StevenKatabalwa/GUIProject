package services;

import java.util.List;

import repository.GenericDAO;
import sql.CatalogRepoImpl;

import domains.Catalog;

public class CatalogServiceImpl implements CatalogService {

	private GenericDAO<Catalog> dao=new CatalogRepoImpl();
	
	@Override
	public Catalog create(Catalog obj) {
		// TODO Auto-generated method stub
		return dao.create(obj);
	}

	@Override
	public Catalog find(long id) {
		// TODO Auto-generated method stub
		return dao.find(id);
	}

	@Override
	public List<Catalog> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Catalog update(Catalog obj) {
		// TODO Auto-generated method stub
		return dao.update(obj);
	}

	@Override
	public Catalog findByName(String name) {
		// TODO Auto-generated method stub
		return dao.findByName(name);
	}

}
