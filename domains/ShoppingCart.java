package domains;

import java.util.HashMap;

public class ShoppingCart extends ItemLister {
	
	private long id;
	private Customer customer;
	private double taxes;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public double getTaxes() {
		return taxes;
	}

	public void setTaxes(double taxes) {
		this.taxes = taxes;
	}
	
	public static class ShoppingCartBuilder{
		
		private ShoppingCart cart=new ShoppingCart();
		
		public ShoppingCartBuilder setid(long id){
			cart.id=id;
			return this;
		}
		
		public ShoppingCartBuilder setCustomer(Customer customer){
			cart.customer=customer;
			return this;
		}
		
		public ShoppingCartBuilder addItems(long product,Integer quantity){
			cart.items.put(product, quantity);
			return this;
		}
		
		public ShoppingCartBuilder addShippingCost(long product,double cost){
			cart.shippingCost.put(product,cost);
			return this;
		}
		
		public ShoppingCart build(){
			return cart;
		}
	}


}
