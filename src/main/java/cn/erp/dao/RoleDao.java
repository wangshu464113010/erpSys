package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Role;


public interface RoleDao {
	List<Role> findAllRoleFy(Integer page,Integer rows) throws SQLException;
	
	List<Role> findAllRole() throws SQLException;
	
	int saveRole(String name,String remarks)throws SQLException;
	
	int deleteRole(Integer id)throws SQLException;
	
	int updateRole(Integer id,String name,String remarks)throws SQLException;
}
