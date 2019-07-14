package sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domains.Product;
import repository.SqlGenericDAO;

public class ProductRepoImpl extends SqlGenericDAO<Product> {

	public ProductRepoImpl(){
		super("Productsdb");
	}
	
	@Override
	public Product create(Product obj) {
		// TODO Auto-generated method stub
		try {
			stmt=con.createStatement();
			ResultSet rs=stmt
			.executeQuery("insert into " +
					"product(catalogid,productname,totalQuantity,priceperunit,mfgdate) " +
					"values('"+obj.getCatalog()+","+obj.getName()+","+obj.getQuantity()+"" +
							","+obj.getUnitPrice()+","+obj.getManufacturingDate()+"')");
			
			endTransaction();
			
			return obj;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;
		}
		
	}

	@Override
	public Product find(long id) {
		// TODO Auto-generated method stub
		try {
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from product where productid='"+id+"'");
			
			Product product=new Product();
			
			while(rs.next()){
				product.setId(rs.getLong("productid"));
				product.setName(rs.getString("productname"));
				product.setQuantity(rs.getInt("totalQuantity"));
				product.setUnitPrice(rs.getDouble("priceperunit"));
				product.setManufacturingDate(rs.getString("mfgdate"));
				product.setCatalog(rs.getLong("catalogid"));
			}	
			
			endTransaction();
			
			return product;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		
		try {
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from product");
			
			List<Product> products=new ArrayList<Product>();
			
			
			while(rs.next()){
				Product product=new Product();
				product.setId(rs.getLong("productid"));
				product.setName(rs.getString("productname"));
				product.setQuantity(rs.getInt("totalQuantity"));
				product.setUnitPrice(rs.getDouble("priceperunit"));
				product.setManufacturingDate(rs.getString("mfgdate"));
				product.setCatalog(rs.getLong("catalogid"));
				
				products.add(product);
			}	
			
			endTransaction();
			return products;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
			}

	@Override
	public Product update(Product obj) {
		// TODO Auto-generated method stub
		try {
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("update product set" +
					"productname='"+obj.getName()+"'," +
					"totalQuantity='"+obj.getQuantity()+"'," +
					"priceperunit='"+obj.getUnitPrice()+"'," +
					"mfgdate='"+obj.getManufacturingDate()+"'," +
					"catalogid='"+obj.getCatalog()+"' " +
					"where productid='"+obj.getId()+"'");
					
			endTransaction();
			
			return obj;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Product findByName(String name) {
		// TODO Auto-generated method stub
		try {
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from product where productname='"+name+"'");
			
			Product product=new Product();
			
			while(rs.next()){
				product.setId(rs.getLong("productid"));
				product.setName(rs.getString("productname"));
				product.setQuantity(rs.getInt("totalQuantity"));
				product.setUnitPrice(rs.getDouble("priceperunit"));
				product.setManufacturingDate(rs.getString("mfgdate"));
				product.setCatalog(rs.getLong("catalogid"));
			}	
			
			endTransaction();
			
			return product;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
