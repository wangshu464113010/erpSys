package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.UserDao;
import cn.erp.domain.Role_Menu;
import cn.erp.domain.User;
import cn.erp.domain.User_Role;
import cn.erp.utils.C3P0Util;

public class UserDaoImpl implements UserDao{

	@Override
	public User findUserById(Integer id) throws SQLException {
		String sql = "select * from t_user where id=?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql,new BeanHandler<User>(User.class),id);
	}

	@Override
	public User findByNameAndPassword(String user_name, String password) throws SQLException {
		String sql = "select * from t_user where user_name=? and password=?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql,new BeanHandler<User>(User.class),user_name,password);
	}

	@Override
	public List<User_Role> findAllUserRoleByUserId(Integer id) throws SQLException {
		String sql = "select * from t_user_role where user_id=?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql,new BeanListHandler<User_Role>(User_Role.class),id);
	}

	@Override
	public List<Role_Menu> findAllRoleMenuByRoleId(Integer id) throws SQLException {
		String sql = "select * from t_role_menu where role_id=?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql,new BeanListHandler<Role_Menu>(Role_Menu.class),id);
	}
	
	public User findOne(int id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_user where id = ?";
		return qr.query(sql, new BeanHandler<User>(User.class),id);
	}
	@Override
	public int updatePassword(User user, String password) throws SQLException {
		String sql = "update t_user set password=? where id=?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.update(sql,password,user.getId());
	}

}
