package cn.erp.service;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Customer;

public interface CustomerService {
	public List<Customer> findAll() throws SQLException;
}
