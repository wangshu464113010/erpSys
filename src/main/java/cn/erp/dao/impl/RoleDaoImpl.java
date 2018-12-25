package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.RoleDao;
import cn.erp.domain.IntegerDO;
import cn.erp.domain.Page;
import cn.erp.domain.Role;
import cn.erp.domain.User;
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

	@Override
	public List<Role> findlikerole(Page page, String name) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql="select * from t_role where name like ? limit ?,?";
		return qr.query(sql, new BeanListHandler<Role>(Role.class),"%"+name+"%",
				(page.getPageNow()-1)*page.getSize(),page.getSize());
	}

	@Override
	public int count() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql="select count(*) as count from t_log";
		IntegerDO query = qr.query(sql, new BeanHandler<IntegerDO>(IntegerDO.class));
		return query.getCount();
	}

	@Override
	public List<Role> findrolefit() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql="select id,name,remarks from t_role";
		return qr.query(sql, new BeanListHandler<Role>(Role.class));
	}

	@Override
	public int insertRoleSet(Integer[] role_id,Integer user_id) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String deleteSql ="delete from t_user_role where user_id = ?";
		qr.update(deleteSql,user_id);//先删除所有权限
		
		String sql="insert into t_user_role(role_id,user_id) values(?,?)";
		for (Integer integer : role_id) {
			qr.update(sql,integer,user_id);
		}
		return 1;
	}

}
