package cn.erp.service;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Customer;
import cn.erp.domain.Page;

public interface CustomerService {
	public List<Customer> findAll() throws SQLException;
	
	
	//wangshu
	public List<Customer> findPageAll(Page page) throws SQLException;
	public List<Customer> findLikePageAll(String name,Page page) throws SQLException;
	public int insert(Customer c) throws SQLException;
	public int update(Customer c) throws SQLException;
	public int delete(int[] id) throws SQLException;
	public int countLikeName(String name) throws SQLException;
	
	
}
