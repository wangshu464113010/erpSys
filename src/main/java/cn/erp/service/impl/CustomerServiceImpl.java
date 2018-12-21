package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.List;

import cn.erp.dao.CustomerDao;
import cn.erp.dao.impl.CustomerDaoImpl;
import cn.erp.domain.Customer;
import cn.erp.domain.Page;
import cn.erp.service.CustomerService;

public class CustomerServiceImpl implements CustomerService{

	private CustomerDao customerDao = new CustomerDaoImpl();
	
	@Override
	public List<Customer> findAll() throws SQLException {
		return customerDao.findAll();
	}

	@Override
	public List<Customer> findPageAll(Page page) throws SQLException {
		return customerDao.findPageAll(page);
	}

	@Override
	public List<Customer> findLikePageAll(String name, Page page) throws SQLException {
		return customerDao.findLikePageAll(name, page);
	}

	@Override
	public int insert(Customer c) throws SQLException {
		return customerDao.insert(c);
	}

	@Override
	public int update(Customer c) throws SQLException {
		return customerDao.insert(c);
	}

	@Override
	public int delete(int[] id) throws SQLException {
		return customerDao.delete(id);
	}

	@Override
	public int countLikeName(String name) throws SQLException {
		return customerDao.countLikeName(name);
	}

}
