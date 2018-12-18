package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.MenuDao;
import cn.erp.domain.Menu;
import cn.erp.utils.C3P0Util;

public class MenuDaoImpl implements MenuDao{
//	private Connection conn = C3P0Util.getConnection();
	
	public List<Menu> findAll() throws SQLException {
		String sql = "select * from t_menu";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql,new BeanListHandler<Menu>(Menu.class));
	}

	@Override
	public List<Menu> findAllByP_id(int p_id) throws SQLException {
		String sql = "select * from t_menu where p_id=?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql,new BeanListHandler<Menu>(Menu.class),p_id);
		
	}
}
