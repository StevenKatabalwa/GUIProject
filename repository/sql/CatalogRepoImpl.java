package repository.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domains.Catalog;
import repository.SqlGenericDAO;

public class CatalogRepoImpl extends SqlGenericDAO<Catalog> {

	public CatalogRepoImpl(){
		super("Productsdb");
	}
	
	@Override
	public Catalog create(Catalog obj) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try {
			stmt=con.createStatement();
			ResultSet rs=stmt
			.executeQuery("insert into " +
					"catalogtype(catalogname) " +
					"values('"+obj.getName()+"'");
			
			endTransaction();
			return obj;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Catalog find(long id) {
		// TODO Auto-generated method stub
		try {
			stmt=con.createStatement();
			ResultSet rs=stmt
			.executeQuery("select * from catalogtype where catalogid='"+id+"'");
			
			Catalog catalog=null;
			
			while(rs.next()){
				catalog=new Catalog((rs.getLong("catalogid")),rs.getString("catalogname"));	
			}
			
			endTransaction();
			return catalog;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Catalog> findAll() {
		// TODO Auto-generated method stub
		try {
			stmt=con.createStatement();
			ResultSet rs=stmt
			.executeQuery("select * from catalogtype");
			
			List<Catalog> catalogs=new ArrayList<Catalog>();
			Catalog catalog=null;
			
			while(rs.next()){
				catalog=new Catalog((rs.getLong("catalogid")),rs.getString("catalogname"));
				catalogs.add(catalog);
			}
			
			endTransaction();
			return catalogs;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Catalog update(Catalog obj) {
		// TODO Auto-generated method stub
		try {
			stmt=con.createStatement();
			ResultSet rs=stmt
			.executeQuery("update catalogtype " +
					" set catalogname='"+obj.getName()+"' where catalogid='"+obj.getId()+"'");
			
			endTransaction();
			return obj;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Catalog findByName(String name) {
		// TODO Auto-generated method stub
		try {
			stmt=con.createStatement();
			ResultSet rs=stmt
			.executeQuery("select * from catalogtype where catalogname='"+name+"'");
			
			Catalog catalog=null;
			
			while(rs.next()){
				catalog=new Catalog((rs.getLong("catalogid")),rs.getString("catalogname"));	
			}
			
			endTransaction();
			return catalog;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
