package cn.erp.service;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.MenuVO;

public interface MenuVOService {
	public List<MenuVO> findAll(int parentId,int roleId) throws SQLException;
	
	
}
