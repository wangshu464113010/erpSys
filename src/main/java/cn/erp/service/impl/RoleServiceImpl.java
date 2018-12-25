package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.List;


import cn.erp.dao.RoleDao;
import cn.erp.dao.impl.RoleDaoImpl;
import cn.erp.domain.Page;
import cn.erp.domain.Role;
import cn.erp.service.RoleService;

public class RoleServiceImpl implements RoleService{

	private RoleDao roleDao=new RoleDaoImpl();
	
	@Override
	public List<Role> findAllRoleFy(Integer page, Integer rows) throws SQLException {
		return this.roleDao.findAllRoleFy(page,rows);
	}

	@Override
	public List<Role> findAllRole() throws SQLException {
		return this.roleDao.findAllRole();
	}

	@Override
	public int saveRole(String name, String remarks) throws SQLException {
		return this.roleDao.saveRole(name,remarks);
	}

	@Override
	public int updateRole(Integer id, String name, String remarks) throws SQLException {
		return this.roleDao.updateRole(id,name,remarks);
	}

	@Override
	public int deleteRole(Integer id) throws SQLException {
		return this.roleDao.deleteRole(id);
	}

	@Override
	public List<Role> findlikerole(Page page, String name) throws SQLException {
		// TODO Auto-generated method stub
		return roleDao.findlikerole(page, name);
	}

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		return roleDao.count();
	}

	@Override
	public List<Role> findrolefit() throws SQLException {
		// TODO Auto-generated method stub
		return roleDao.findrolefit();
	}

	@Override
	public int insertRoleSet(Integer[] role_id, Integer user_id) throws SQLException {
		// TODO Auto-generated method stub
		return roleDao.insertRoleSet(role_id, user_id);
	}

}
