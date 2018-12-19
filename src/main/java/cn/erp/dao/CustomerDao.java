package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Customer;

public interface CustomerDao {
	public List<Customer> findAll() throws SQLException;
	public Customer findById(int id) throws SQLException;
}
