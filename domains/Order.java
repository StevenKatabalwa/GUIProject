package domains;

import java.util.HashMap;
import java.util.List;

import utilities.ShippingStatus;

public class Order {
	
	private long id;
	private long customer;
	private String date;
	private Address billingAddress;
	private Address shippingAddress;
	private String shippingDate;
	private String deliveryDate;
	private ShippingStatus shippingStatus;
	private OrderList items;
	private double totalOrderAmmount;
	private double totalTaxAmount;
	private double totalItemsPrice;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCustomer() {
		return customer;
	}

	public void setCustomer(long customer) {
		this.customer = customer;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(String shippingDate) {
		this.shippingDate = shippingDate;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public ShippingStatus getShippingStatus() {
		return shippingStatus;
	}

	public void setShippingStatus(ShippingStatus shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	public OrderList getOrderList() {
		return items;
	}

	public void setOrderList(OrderList items) {
		this.items = items;
	}

	public double getTotalOrderAmmount() {
		return totalOrderAmmount;
	}

	public void setTotalOrderAmmount(double totalOrderAmmount) {
		this.totalOrderAmmount = totalOrderAmmount;
	}

	public double getTotalTaxAmount() {
		return totalTaxAmount;
	}

	public void setTotalTaxAmount(double totalTaxAmount) {
		this.totalTaxAmount = totalTaxAmount;
	}

	public double getTotalItemsPrice() {
		return totalItemsPrice;
	}

	public void setTotalItemsPrice(double totalItemsPrice) {
		this.totalItemsPrice = totalItemsPrice;
	}

	
	public static class OrderBuilder{
		
		private Order order=new Order();
		
		public OrderBuilder(){}
		
		public OrderBuilder(long customer){
			order.customer=customer;
		}
		
		public OrderBuilder setId(long id){
			order.id=id;
			return this;
		}
		
		public OrderBuilder setCustomer(long customer){
			order.customer=customer;
			return this;
		}
		
		public OrderBuilder setOrderDate(String date){
			order.date=date;
			return this;
		}
		
		public OrderBuilder setBillingAddress(Address billingAddress){
			order.billingAddress=billingAddress;
			return this;
		}
		
		public OrderBuilder setShippingAddress(Address shippingAddress){
			order.shippingAddress=shippingAddress;
			return this;
		}
		
		public OrderBuilder setShippingDate(String date){
			order.shippingDate=date;
			return this;
		}
		
		public OrderBuilder setDeliveryDate(String date){
			order.deliveryDate=date;
			return this;
		}
		
		public OrderBuilder setShippingStatus(ShippingStatus status){
			order.shippingStatus=status;
			return this;
		}
		
		public OrderBuilder setOrderList(OrderList items){
			order.items=items;
			return this;
		}
		
		public Order build(){
			return order;
		}
	}
}
