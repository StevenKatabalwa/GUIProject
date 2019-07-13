package domains;

import java.util.Date;

public class Product {
	
	private String name;
	private long id;
	private String manufacturingDate;
	private int quantity;
	private String description;
	private double unitPrice;
	private long catalog;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getManufacturingDate() {
		return manufacturingDate;
	}
	public void setManufacturingDate(String manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public void setCatalog(long catalogId){
		this.catalog=catalogId;
	}
	
	public long getCatalog(){
		return this.catalog;
	}
	
	private static class ProductBuilder{
		
		private Product product=new Product();
		
		public ProductBuilder setId(long id){
			product.id=id;
			return this;
		}
		
		public ProductBuilder setName(String name){
			product.name=name;
			return this;
		}
		
		public ProductBuilder setDescription(String description){
			product.description=description;
			return this;
		}
		
		public ProductBuilder setUnitPrice(double unitPrice){
			product.unitPrice=unitPrice;
			return this;
		}
		
		public ProductBuilder setQuantity(int quantity) {
			product.quantity = quantity;
			return this;
		}	
	
		public ProductBuilder setManufacturingDate(String mfgDate){
			product.manufacturingDate=mfgDate;
			return this;
		}
		
		public ProductBuilder setCatalog(long catalog){
			product.catalog=catalog;
			return this;
		}
		
		public Product Build(){
			return product;
		}
	}
}
