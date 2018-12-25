package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Page;
import cn.erp.domain.Role;


public interface RoleDao {
	List<Role> findAllRoleFy(Integer page,Integer rows) throws SQLException;
	
	List<Role> findAllRole() throws SQLException;
	
	int saveRole(String name,String remarks)throws SQLException;
	
	int deleteRole(Integer id)throws SQLException;
	
	int updateRole(Integer id,String name,String remarks)throws SQLException;
	
	List<Role> findlikerole(Page page,String name) throws SQLException;
	
	public int count() throws SQLException;//查询数量
	
	public List<Role> findrolefit() throws SQLException;//查找角色表里面的角色
	
	public int insertRoleSet(Integer[] role_id,Integer user_id)throws SQLException;//向角色表中添加信息
}
