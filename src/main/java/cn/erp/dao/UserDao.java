package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Page;
import cn.erp.domain.Role_Menu;
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
	
	public void  insertUser (User user)throws SQLException;//添加一个用户
	
	public List<User> findAllUser(Page page) throws SQLException;//查找user表里面所有的用户
	
	public List<User> findlikeUser(Page page,String userName) throws SQLException;//模糊查找user表里面所有的用户
	
	public int count() throws SQLException;//查询数量
	
	public void deleteUser(Integer id)throws SQLException;//删除用户
	
}
