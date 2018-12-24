package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.UserDao;
import cn.erp.domain.DamageList;
import cn.erp.domain.User;
import cn.erp.utils.C3P0Util;

public class UserDaoImpl implements UserDao{

	@Override
	public User findOne(int id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_user where id = ?";
		return qr.query(sql, new BeanHandler<User>(User.class),id);
	}

	@Override
	public List<User> findAll() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_user";
		List<User> list =  qr.query(sql,new BeanListHandler<User>(User.class));
		return list;
	}

	@Override
	public User findOne(String userTrueName) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_user where true_name like ?";
		return qr.query(sql, new BeanHandler<User>(User.class),"%"+userTrueName+"%");
	}

}
