package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

<<<<<<< HEAD
import cn.erp.domain.Role_Menu;
=======
>>>>>>> refs/heads/wy3
import cn.erp.domain.User;
import cn.erp.domain.User_Role;

public interface UserDao {
	User findUserById(Integer id) throws SQLException;
	
	User findByNameAndPassword(String user_name,String password)throws SQLException;
	
	List<User_Role> findAllUserRoleByUserId(Integer id) throws SQLException;
	
	List<Role_Menu> findAllRoleMenuByRoleId(Integer id) throws SQLException;
	
	int updatePassword(User user,String password)throws SQLException;

	public User findOne(int id)throws SQLException;
	public List<User> findAll()throws SQLException;
	public User findOne(String userTrueName)throws SQLException;
}
