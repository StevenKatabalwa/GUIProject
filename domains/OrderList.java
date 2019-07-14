package domains;

public class OrderList extends ItemLister {

	public OrderList(){
		super();
	}
	
	
	public static class OrderListBuilder{
		
		private OrderList orderList=new OrderList();
		
		public OrderListBuilder addItems(long product,int quantity){
			orderList.items.put(product, quantity);
			return this;
		}
		
		public OrderListBuilder addShippingCost(long product,double cost){
			orderList.shippingCost.put(product, cost);
			return this;
		}
		
		public OrderList build(){
			return orderList;
		}
	}
	
}
