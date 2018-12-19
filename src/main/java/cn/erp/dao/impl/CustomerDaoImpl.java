package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.CustomerDao;
import cn.erp.domain.Customer;
import cn.erp.domain.Goods;
import cn.erp.utils.C3P0Util;

public class CustomerDaoImpl implements CustomerDao{

	@Override
	public List<Customer> findAll() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_customer";
		List<Customer> list =  qr.query(sql,new BeanListHandler<Customer>(Customer.class));
		return list;
	}

	@Override
	public Customer findById(int id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_customer where id=?";
		Customer customer = qr.query(sql,new BeanHandler<Customer>(Customer.class),id);
		return customer;
	}

}
