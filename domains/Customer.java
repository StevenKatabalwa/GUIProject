package domains;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	private long id;
	private String firstName;
	private String lastName;
	private String ssn;
	private Account account;
	private List<Address> addresses=new ArrayList<Address>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	
	public void addAddress(Address address){
		addresses.add(address);
	}
	
	public static class CustomerBuilder{
		
		private Customer customer=new Customer();
		
		public CustomerBuilder setId(long id){
			customer.id=id;
			return this;
		}
		
		public CustomerBuilder setFirstname(String firstName){
			customer.firstName=firstName;
			return this;
		}
		
		public CustomerBuilder setLastName(String lastName){
			customer.lastName=lastName;
			return this;
		}

		public CustomerBuilder setSSN(String ssn){
			customer.ssn=ssn;
			return this;
		}
		
		public CustomerBuilder setAccount(Account account){
			customer.account=account;
			return this;
		}
		
		public CustomerBuilder addAddress(Address address){
			customer.addresses.add(address);
			return this;
		}
		
		public Customer build(){
			return customer;
		}
	}
	
}
