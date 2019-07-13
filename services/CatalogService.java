package services;

import domains.Catalog;

public interface CatalogService extends GenericService<Catalog> {

	public Catalog findByName(String name);
	
}
