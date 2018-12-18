package cn.erp.service;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Menu;

public interface MenuService {
	public List<Menu> menuList()throws SQLException;
}
