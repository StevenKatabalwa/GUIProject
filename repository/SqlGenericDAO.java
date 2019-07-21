package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public abstract class SqlGenericDAO<T> implements GenericDAO<T> {

	protected Connection con = null;
	protected Statement stmt = null;
	protected String dburl = "jdbc:mysql:///";
	protected String insertStmt = "";
	protected String selectStmt = "";
	private String user="root";
	private String password="root";

	public SqlGenericDAO(String db){
		
		dburl=dburl+db;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			//debug
			e.printStackTrace();
		}
		try{
			startTransaction();
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

	protected void startTransaction() throws SQLException{
		con = DriverManager.getConnection(dburl, "root", "root");
	}

	protected void endTransaction() throws SQLException{
		
		if(!stmt.isClosed()) stmt.close();
		
		if(!con.isClosed())con.close();
	}
}
