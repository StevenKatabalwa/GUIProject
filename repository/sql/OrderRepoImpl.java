package repository.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import repository.SqlGenericDAO;
import domains.Address;
import domains.Order;
import domains.OrderList;

public class OrderRepoImpl extends SqlGenericDAO<Order> {

	public OrderRepoImpl(){
		super("Accountsdb");
	}
	
	@Override
	public Order create(Order obj) {
		// TODO Auto-generated method stub
//		try {
//			stmt=con.createStatement();
//			ResultSet rs=stmt
//			.executeQuery("insert into " +
//					"ord(custid,shipaddress1,shipaddress2,shipcity,shipstate,shipzipcode,billaddress1,billaddress2," +
//					"billcity,billstate,billzipcode,nameoncard,expdate,cardtype,cardnum,orderdate,shippeddate,delivereddate,shipstatus)" +
//					"values('"+obj.getCatalog()+","+obj.getName()+","+obj.getQuantity()+"" +
//							","+obj.getUnitPrice()+","+obj.getManufacturingDate()+"')");
//			
//			endTransaction();
//			
//			return obj;
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//
//			return null;
//		}
//		
	
		return null;
	}

	@Override
	public Order find(long id) {
		// TODO Auto-generated method stub
		try {
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from ord where orderid='"+id+"'");
			
			Order order=null;
			
			while(rs.next()){
				
				Address billingAddress=new Address.AddressBuilder()
				.setStreet(rs.getString("billaddress1"))
				.setZipCode(rs.getInt("billzipcode"))
				.setState(rs.getString("billstate"))
				.setCity("billcity")
				.Build();
				
				Address shippingAddress=new Address.AddressBuilder()
				.setStreet(rs.getString("shipaddress1"))
				.setZipCode(rs.getInt("shipzipcode"))
				.setState(rs.getString("shipstate"))
				.setCity("shipcity")
				.Build();
				
				 order=new Order.OrderBuilder()
				.setId(rs.getLong("orderid"))
				.setOrderDate(rs.getString("orderdate"))
				.setBillingAddress(billingAddress)
				.setShippingAddress(shippingAddress)
				.setCustomer(rs.getLong("custid"))
				.build();				
				
			}	
			        
		    order.setOrderList(getOrderListAsync(order.getId()));
		   
			endTransaction();
			
			return order;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		try {
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from ord");
			
			List<Order> orders=new ArrayList<Order>();
			
			while(rs.next()){
				
				Address billingAddress=new Address.AddressBuilder()
				.setStreet(rs.getString("billaddress1"))
				.setZipCode(rs.getInt("billzipcode"))
				.setState(rs.getString("billstate"))
				.setCity("billcity")
				.Build();
				
				Address shippingAddress=new Address.AddressBuilder()
				.setStreet(rs.getString("shipaddress1"))
				.setZipCode(rs.getInt("shipzipcode"))
				.setState(rs.getString("shipstate"))
				.setCity("shipcity")
				.Build();
				
				 final Order order=new Order.OrderBuilder()
				.setId(rs.getLong("orderid"))
				.setOrderDate(rs.getString("orderdate"))
				.setBillingAddress(billingAddress)
				.setShippingAddress(shippingAddress)
				.setCustomer(rs.getLong("custid"))
				.build();	
				 
				 //run asynchronously to get the list of items for each order
				 new Thread(new Runnable() {
				     @Override
				     public void run() {			          
				    	 order.setOrderList(getOrderListAsync(order.getId()));
				     }
				}).start();
				 
				orders.add(order);
			}	
			
			//endTransaction();
			
			return orders;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Order findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order update(Order obj) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private OrderList getOrderListAsync(long id){
		
		try {
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from ord o inner join orderitem oi" +
					" on o.orderid=oi.orderid where o.orderid='"+id+"'");
			
			OrderList orderList=new OrderList();
			
			while(rs.next()){
				orderList.addItems(rs.getLong("productid"),rs.getInt("quantity"));
				orderList.addShippingCost(rs.getLong("productid"),rs.getDouble("shipmentcost"));
			}
			
			return orderList;
		}
		
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
