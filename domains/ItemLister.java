package domains;

import java.util.HashMap;

public abstract class ItemLister {
	
	protected HashMap<Long,Integer> items=new HashMap<Long,Integer>();
	protected HashMap<Long,Double> shippingCost=new HashMap<Long,Double>();
	
	public HashMap<Long, Integer> getItems() {
		return items;
	}

	public HashMap<Long, Double> getShippingCost() {
		return shippingCost;
	}
	
	public void addItems(long product,Integer quantity){
		this.items.put(product, quantity);
	}
	
	public void addShippingCost(long product,double cost){
		this.shippingCost.put(product,cost);
	}
	
}
