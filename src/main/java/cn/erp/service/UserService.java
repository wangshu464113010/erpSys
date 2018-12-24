package cn.erp.service;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.User;
import cn.erp.domain.User_Role;

public interface UserService {
	User login(String user_name,String password) throws SQLException;
	
	List<User_Role> findAllUserRoleByUserId(Integer id)throws SQLException;
	
	int updatePassword(User user,String password)throws SQLException;
	public User findByUser_id(int id) throws SQLException;
	
}
