package services;

import java.util.List;

import repository.GenericDAO;
import sql.CustomerRepoImpl;

import domains.Customer;

public class CustomerServiceImpl implements CustomerService {

	private GenericDAO<Customer> dao=new CustomerRepoImpl();
	
	@Override
	public Customer create(Customer obj) {
		// TODO Auto-generated method stub
		return dao.create(obj);
	}

	@Override
	public Customer find(long id) {
		// TODO Auto-generated method stub
		return dao.find(id);
	}

	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Customer update(Customer obj) {
		// TODO Auto-generated method stub
		return dao.update(obj);
	}

}
