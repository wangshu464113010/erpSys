package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.RoleDao;
import cn.erp.domain.Role;
import cn.erp.utils.C3P0Util;

public class RoleDaoImpl implements RoleDao{

	@Override
	public List<Role> findAllRoleFy(Integer page, Integer rows) throws SQLException {
		String sql = "select * from t_role limit ?,?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql,new BeanListHandler<Role>(Role.class),(page-1)*rows,rows);
	}

	@Override
	public List<Role> findAllRole() throws SQLException {
		String sql = "select * from t_role";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql,new BeanListHandler<Role>(Role.class));
	}

	@Override
	public int saveRole(String name, String remarks) throws SQLException {
		String sql = "insert into t_role(name,remarks) value(?,?) ";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.update(sql,name,remarks);
	}

	@Override
	public int updateRole(Integer id, String name, String remarks) throws SQLException {
		String sql = "update t_role set name=?,remarks=? where id=? ";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.update(sql,name,remarks,id);
	}

	@Override
	public int deleteRole(Integer id) throws SQLException {
		String sql = "delete from t_role where id=? ";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.update(sql,id);
	}

}
