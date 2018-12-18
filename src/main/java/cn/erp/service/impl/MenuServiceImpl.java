package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.erp.dao.MenuDao;
import cn.erp.dao.impl.MenuDaoImpl;
import cn.erp.domain.Menu;
import cn.erp.service.MenuService;

public class MenuServiceImpl implements MenuService {
	
	private MenuDao menuDao = new MenuDaoImpl();
	
	@Override
	public List<Menu> menuList() throws SQLException {
		List<Menu> list = menuDao.findAllByP_id(-1);
		Menu menuParent = list.remove(0);
		menuParent.getAttributes().setUrl(menuParent.getUrl());
		List<Menu> listChild1 = menuDao.findAllByP_id(menuParent.getId());
		for (Menu menu : listChild1) {
			menu.getAttributes().setUrl(menu.getUrl());
			menuParent.getChildren().add(menu);
			
			List<Menu> listChild2 = menuDao.findAllByP_id(menu.getId());
			for (Menu menu2 : listChild2) {
				menu2.getAttributes().setUrl(menu2.getUrl());
				menu.getChildren().add(menu2);
			}
		}
		list.add(menuParent);
		return list;
	}

}
