package domains;

public class Account {

	private String userName;
	private String password;
	
	public Account(){}
	
	public Account(String userName){
		this.userName=userName;
	}

	public Account(String userName,String password){
		this.userName=userName;
		this.password=password;
	}
	
}
