package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.MenuVODao;
import cn.erp.domain.IntegerDO;
import cn.erp.domain.MenuVO;
import cn.erp.utils.C3P0Util;

public class MenuVODaoImpl implements MenuVODao {
	 
	@Override
	public List<MenuVO> findAll() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select id,icon,name text,state,p_id from t_menu";
		return qr.query(sql, new BeanListHandler<MenuVO>(MenuVO.class));
	}

	@Override
	public List<MenuVO> findAllByP_id(Integer p_id)throws SQLException  {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select id,icon,name text,p_id from t_menu"
				+ " where p_id = ?";
		return qr.query(sql, new BeanListHandler<MenuVO>(MenuVO.class),p_id);
	}

	public IntegerDO findStateById(Integer id)throws SQLException  {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select state from t_menu"
				+ " where id = ?";
		return qr.query(sql, new BeanHandler<IntegerDO>(IntegerDO.class),id);
	}
	@Override
	public IntegerDO findCheckByMenuIdAndRoleId(int menuId, int roleId) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select count(*) count from t_role_menu "
				+ " where menu_id = ? and role_id=?";
		return qr.query(sql, new BeanHandler<IntegerDO>(IntegerDO.class),menuId,roleId);
	
	}

}
