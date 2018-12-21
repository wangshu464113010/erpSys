package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.erp.dao.MenuDao;
import cn.erp.dao.UserDao;
import cn.erp.dao.impl.MenuDaoImpl;
import cn.erp.dao.impl.UserDaoImpl;
import cn.erp.domain.Menu;
import cn.erp.domain.Role_Menu;
import cn.erp.domain.User_Role;
import cn.erp.service.MenuService;

public class MenuServiceImpl implements MenuService {
	
	private MenuDao menuDao = new MenuDaoImpl();
	private UserDao userDao = new UserDaoImpl();
	
	@Override
	public List<Menu> menuList(Integer userid) throws SQLException {
		List<User_Role> list1 = this.userDao.findAllUserRoleByUserId(userid);
		List<Role_Menu> list2=new ArrayList<>();
		for (User_Role user_Role : list1) {
			list2.addAll(this.userDao.findAllRoleMenuByRoleId(user_Role.getRole_id()));
		}
		List<Menu> list = menuDao.findAllByP_id(-1);
		Menu menuParent = list.remove(0);
		menuParent.getAttributes().setUrl(menuParent.getUrl());
		List<Menu> listChild1 = this.menuDao.findAllByP_id(menuParent.getId());//第一层
		List<Menu> listChild2=new ArrayList<>();
		for (Menu menu : listChild1) {
			for (Role_Menu role_menu : list2) {
				if(role_menu.getMenu_id().intValue()==menu.getId().intValue()){
					menu.getAttributes().setUrl(menu.getUrl());
					listChild2.add(menu);
				}
			}
		}
		for (Menu menu : listChild2) {
			List<Menu> listMenu = this.menuDao.findAllByP_id(menu.getId());//第二层
			for (Menu menu2 : listMenu) {
				for (Role_Menu role_menu : list2) {
					if(role_menu.getMenu_id().intValue()==menu2.getId().intValue()){
						menu2.getAttributes().setUrl(menu2.getUrl());
						menu.getChildren().add(menu2);
					}
				}
			}
			menuParent.getChildren().add(menu);
		}
		list.add(menuParent);
		return list;
	}

}
