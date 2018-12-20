package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.taglibs.standard.lang.jstl.IntegerDivideOperator;

import cn.erp.dao.CustomerDao;
import cn.erp.domain.Customer;
import cn.erp.domain.IntegerDO;
import cn.erp.domain.Page;
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

	@Override
	public List<Customer> findPageAll(Page page) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_customer limit ?,?";
		return qr.query(sql, new BeanListHandler<Customer>(Customer.class),
				page.getSize()*(page.getPageNow()-1),page.getSize());
	}

	@Override
	public List<Customer> findLikePageAll(String name, Page page) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_customer where name like ? limit ?,?";
		return qr.query(sql, new BeanListHandler<Customer>(Customer.class),"%"+name+"%",
				page.getSize()*(page.getPageNow()-1),page.getSize());
	}

	@Override
	public int insert(Customer c) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "insert into t_customer(address,contact,name,number,remarks) "
				+ " values(?,?,?,?,?)";
		return qr.update(sql,c.getAddress(),c.getContact(),c.getName(),c.getNumber(),c.getRemarks());
		 
	}

	@Override
	public int update(Customer c) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "update t_customer set address=?,contact=?,name=?,number=?,remarks=?"
				+ " where id=?";
		return qr.update(sql,c.getAddress(),
				c.getContact(),c.getName(),c.getNumber(),c.getRemarks(),c.getId());
		 
	}

	@Override
	public int delete(int[] id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String s = "";
		for (int i : id) {
			s = s+i+",";
		}
		return qr.update("delete from t_customer where id in ("+s.substring(0, s.length()-1)+")");
	}

	@Override
	public int countLikeName(String name) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select count(*) as count from t_customer where name like ? ";
		IntegerDO query = qr.query(sql, new BeanHandler<IntegerDO>(IntegerDO.class),"%"+name+"%");
		return query.getCount();
	}
	
	
	

}
