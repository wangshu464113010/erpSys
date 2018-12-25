package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.IntegerDO;
import cn.erp.domain.MenuVO;

public interface MenuVODao {
	public List<MenuVO> findAll() throws SQLException;
	public List<MenuVO> findAllByP_id(Integer p_id) throws SQLException;
	public IntegerDO findCheckByMenuIdAndRoleId(int menuId, int roleId)throws SQLException;
	public IntegerDO findStateById(Integer id)throws SQLException;
	
}
