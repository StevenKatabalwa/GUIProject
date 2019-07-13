package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public abstract class SqlGenericDAO<T> implements GenericDAO<T> {

	protected Connection con = null;
	protected Statement stmt = null;
	protected String dburl = "jdbc:mysql:///ProductsDb";
	protected String insertStmt = "";
	protected String selectStmt = "";
	
	public SqlGenericDAO(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			//debug
			e.printStackTrace();
		}
		try{
			con = DriverManager.getConnection(dburl, "root", "root");
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ResultSet operate(String insertStatement){
		
		try {
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(insertStatement);
			return rs;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}
	
	protected void endTransaction() throws SQLException{
		
		if(!stmt.isClosed()) stmt.close();
		
		if(!con.isClosed())con.close();
	}
}
