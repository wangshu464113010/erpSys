package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.List;

import cn.erp.dao.CustomerDao;
import cn.erp.dao.impl.CustomerDaoImpl;
import cn.erp.domain.Customer;
import cn.erp.service.CustomerService;

public class CustomerServiceImpl implements CustomerService{

	private CustomerDao customerDao = new CustomerDaoImpl();
	
	@Override
	public List<Customer> findAll() throws SQLException {
		return customerDao.findAll();
	}

}
