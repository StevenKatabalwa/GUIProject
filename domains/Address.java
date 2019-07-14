package domains;

public class Address {

	private long id;
	private String street;
	private String city;
	private String state;
	private int zipCode;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipCode() {
		return zipCode;
	}
	
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	
	public static class AddressBuilder {

		private Address address=new Address();
		
		public AddressBuilder setId(long id) {
			address.id = id;
			return this;
		}

		public AddressBuilder setStreet(String street) {
			address.street = street;
			return this;
		}

		public AddressBuilder setCity(String city) {
			address.city=city;
			return this;
		}

		public AddressBuilder setState(String state) {
			address.state=state;
			return this;
		}

		public AddressBuilder setZipCode(int zipCode) {
			address.zipCode = zipCode;
			return this;
		}
		
		public Address Build(){
			return address;
		}
	}



}
