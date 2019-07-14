package sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domains.Account;
import domains.Address;
import domains.Customer;
import domains.Order;
import repository.SqlGenericDAO;

public class CustomerRepoImpl extends SqlGenericDAO<Customer> {

	public CustomerRepoImpl(){
		super("Accountsdb");
	}
	
	@Override
	public Customer create(Customer obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer find(long id) {
		// TODO Auto-generated method stub
		try {
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from customer where custid='"+id+"'");
			
			Customer customer=null;
			
			while(rs.next()){
				
				Address address=new Address.AddressBuilder()
				.setStreet(rs.getString("shipaddress1"))
				.setZipCode(rs.getInt("shipzipcode"))
				.setState(rs.getString("shipstate"))
				.setCity("shipcity")
				.Build();
				
				 customer=new Customer.CustomerBuilder()
				 .setId(rs.getLong("custid"))
				 .setFirstname(rs.getString("fname"))
				 .setLastName(rs.getString("lname"))
				 .setSSN(rs.getString("ssn"))
				 .setAccount(new Account(rs.getString("login")))
				 .addAddress(address)
				 .build();
				
			}	
			
			endTransaction();
			
			return customer;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Customer> findAll() {
		
		try {
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from customer");
			
			List<Customer> customers=new ArrayList<Customer>();
			
			while(rs.next()){
				
				Address address=new Address.AddressBuilder()
				.setStreet(rs.getString("shipaddress1"))
				.setZipCode(rs.getInt("shipzipcode"))
				.setState(rs.getString("shipstate"))
				.setCity("shipcity")
				.Build();
				
				Customer customer=new Customer.CustomerBuilder()
				 .setId(rs.getLong("custid"))
				 .setFirstname(rs.getString("fname"))
				 .setLastName(rs.getString("lname"))
				 .setSSN(rs.getString("ssn"))
				 .setAccount(new Account(rs.getString("login")))
				 .addAddress(address)
				 .build();
				
				customers.add(customer);
			}	
			
			endTransaction();
			
			return customers;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Customer findByName(String name) {
		// TODO Auto-generated method stub
		
		String [] nameArr=name.split(" ");
		
		try {
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from customer where fname='"+nameArr[0]+"' and lname='"+nameArr[1]+"'");
			
			Customer customer=null;
			
			while(rs.next()){
				
				Address address=new Address.AddressBuilder()
				.setStreet(rs.getString("shipaddress1"))
				.setZipCode(rs.getInt("shipzipcode"))
				.setState(rs.getString("shipstate"))
				.setCity("shipcity")
				.Build();
				
				 customer=new Customer.CustomerBuilder()
				 .setId(rs.getLong("custid"))
				 .setFirstname(rs.getString("fname"))
				 .setLastName(rs.getString("lname"))
				 .setSSN(rs.getString("ssn"))
				 .setAccount(new Account(rs.getString("login")))
				 .addAddress(address)
				 .build();
				
			}	
			
			endTransaction();
			
			return customer;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Customer update(Customer obj) {
		// TODO Auto-generated method stub
		return null;
	}

		
	
}
