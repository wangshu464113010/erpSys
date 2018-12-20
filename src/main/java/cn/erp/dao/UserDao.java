package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Role_Menu;
import cn.erp.domain.User;
import cn.erp.domain.User_Role;

public interface UserDao {
	User findUserById(Integer id) throws SQLException;
	
	User findByNameAndPassword(String user_name,String password)throws SQLException;
	
	List<User_Role> findAllUserRoleByUserId(Integer id) throws SQLException;
	
	List<Role_Menu> findAllRoleMenuByRoleId(Integer id) throws SQLException;
}
