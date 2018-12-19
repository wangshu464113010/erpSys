package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.RoleDao;
import cn.erp.utils.C3P0Util;

public class RoleDaoImpl implements RoleDao{

	@Override
	public List<User_Role> findByUserId(Integer id) throws SQLException {
		String sql = "select * from t_user_role where user_id=?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql,new BeanListHandler<User_Role>(User_Role.class),id);
	}
	
}
