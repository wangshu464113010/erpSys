package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.List;


import com.alibaba.fastjson.JSONObject;

import cn.erp.dao.MenuVODao;
import cn.erp.dao.impl.MenuVODaoImpl;
import cn.erp.domain.IntegerDO;
import cn.erp.domain.MenuVO;
import cn.erp.service.MenuVOService;

public class MenuVOServiceImpl implements MenuVOService {
	
	private MenuVODao menuVODao = new MenuVODaoImpl();
	
	public List<MenuVO> findAll(int parentId, int roleId) throws SQLException {
		List<MenuVO> parentList = menuVODao.findAllByP_id(parentId);
		for (MenuVO menuVO : parentList) {
			Integer p_id = menuVO.getId();//第二层菜单的父id=第一菜单的id
			
			//查询第一层菜单是否被选中
			IntegerDO MenuIdAndRoleId1 = menuVODao.findCheckByMenuIdAndRoleId(
					menuVO.getId(), roleId);
			if(MenuIdAndRoleId1!= null && MenuIdAndRoleId1.getCount()!=null 
					&& MenuIdAndRoleId1.getCount()>=1) {
				menuVO.setChecked(true);
			}else {
				menuVO.setChecked(null);
			}
			IntegerDO stateById1 = menuVODao.findStateById(menuVO.getId());
			if(stateById1!= null && stateById1.getCount()!=null 
					&& stateById1.getCount()>=1) {
				menuVO.setState("colsed");
			}else {
				menuVO.setState("open");
			}
			
			List<MenuVO> childrenList = menuVODao.findAllByP_id(p_id);//查询第二层菜单
			menuVO.setChildren(childrenList);//第二层菜单注入第一菜单里面
			for (MenuVO menuVO2 : parentList) {//遍历第1层菜单
				//查询第1层菜单是否被选中
				IntegerDO MenuIdAndRoleId2 = menuVODao.findCheckByMenuIdAndRoleId(
						menuVO2.getId(), roleId);
				if(MenuIdAndRoleId2!= null && MenuIdAndRoleId2.getCount()!=null 
						&& MenuIdAndRoleId2.getCount()>=1) {
					menuVO2.setChecked(true);
				}else {
					menuVO2.setChecked(null);
				}
				IntegerDO stateById2 = menuVODao.findStateById(menuVO2.getId());
				if(stateById2!= null && stateById2.getCount()!=null 
						&& stateById1.getCount()>=1) {
					menuVO2.setState("colsed");
				}else {
					menuVO2.setState("open");
				}

				List<MenuVO> children = menuVODao.findAllByP_id(menuVO2.getId());//查询第2层菜单
				for (MenuVO menuVO3 : children) {
					//查询第2层菜单是否被选中
					IntegerDO MenuIdAndRoleId3 = menuVODao.findCheckByMenuIdAndRoleId(
							menuVO3.getId(), roleId);
					if(MenuIdAndRoleId3!= null && MenuIdAndRoleId3.getCount()!=null 
							&& MenuIdAndRoleId3.getCount()>=1) {
						menuVO3.setChecked(true);
					}else {
						menuVO3.setChecked(null);
					}
					IntegerDO stateById3 = menuVODao.findStateById(menuVO3.getId());
					if(stateById3!= null && stateById3.getCount()!=null 
							&& stateById3.getCount()>=1) {
						menuVO3.setState("colsed");
					}else {
						menuVO3.setState("open");
					}

					List<MenuVO> children3 = menuVODao.findAllByP_id(menuVO3.getId());//查询第3层菜单
					for (MenuVO menuVO4 : children3) {
						IntegerDO MenuIdAndRoleId4 = menuVODao.findCheckByMenuIdAndRoleId(
								menuVO4.getId(), roleId);
						if(MenuIdAndRoleId4!= null && MenuIdAndRoleId4.getCount()!=null 
								&& !MenuIdAndRoleId4.getCount().equals("") && MenuIdAndRoleId4.getCount()>=1) {
							menuVO4.setChecked(true);
						}else {
							menuVO4.setChecked(null);
						}
						IntegerDO stateById4 = menuVODao.findStateById(menuVO4.getId());
						if(stateById4!= null && stateById4.getCount()!=null 
								&& stateById4.getCount()>=1) {
							menuVO4.setState("colsed");
						}else {
							menuVO4.setState("open");
						}

						
					}
					menuVO3.setChildren(children3);
				}
				
				menuVO2.setChildren(children);
			}
		}
		Object json = JSONObject.toJSON(parentList);
		System.out.println(json.toString());
		return parentList;
	}
}
