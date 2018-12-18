package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Menu;

public interface MenuDao {
	public List<Menu> findAll()throws SQLException;
	public List<Menu> findAllByP_id(int p_id) throws SQLException;
}
