package domains;

import java.util.ArrayList;
import java.util.List;

public class Catalog {

	private long id;
	private String name;
	private List<Product> products=new ArrayList<Product>();
	
	public Catalog(){};
	
	public Catalog(String name){
		this.name=name;
	}
	
	public Catalog(long id,String name){
		this.id=id;
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}
	
	public void addProduct(Product product){
		this.products.add(product);
	}
	
	public List<Product> getProducts(){
		return this.products;
	}
	
}
