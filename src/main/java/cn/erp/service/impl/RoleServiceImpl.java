package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.List;


import cn.erp.dao.RoleDao;
import cn.erp.dao.impl.RoleDaoImpl;
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

}
