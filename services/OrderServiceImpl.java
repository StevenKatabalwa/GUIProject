package services;

import java.util.List;

import repository.GenericDAO;
import sql.OrderRepoImpl;

import domains.Order;

public class OrderServiceImpl implements OrderService {

	GenericDAO<Order> dao=new OrderRepoImpl();
	
	@Override
	public Order create(Order obj) {
		// TODO Auto-generated method stub
		return dao.create(obj);
	}

	@Override
	public Order find(long id) {
		// TODO Auto-generated method stub
		return dao.find(id);
	}

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Order update(Order obj) {
		// TODO Auto-generated method stub
		return dao.update(obj);
	}

}
