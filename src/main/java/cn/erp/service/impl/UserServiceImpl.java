package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.List;

import cn.erp.dao.UserDao;
import cn.erp.dao.impl.UserDaoImpl;
import cn.erp.domain.Page;
import cn.erp.domain.User;
import cn.erp.domain.User_Role;
import cn.erp.service.UserService;

public class UserServiceImpl implements UserService{

	private UserDao userDao=new UserDaoImpl();
	
	@Override
	public User findByUser_id(int id) throws SQLException {
		return userDao.findOne(id);
	}
	@Override
	public User login(String user_name, String password)throws SQLException {
		
		return this.userDao.findByNameAndPassword(user_name,password);
	}

	@Override
	public List<User_Role> findAllUserRoleByUserId(Integer id) throws SQLException {
		return this.userDao.findAllUserRoleByUserId(id);
	}

	@Override
	public int updatePassword(User user, String password) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void insertUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		userDao.insertUser(user);
	}
	@Override
	public List<User> findAllUser(Page page) throws SQLException {
		return  userDao.findAllUser(page);
	}
	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		return userDao.count();
	}
	@Override
	public void deleteUser(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		userDao.deleteUser(id);
	}
	@Override
	public List<User> findlikeUser(Page page, String userName) throws SQLException {
		// TODO Auto-generated method stub
		return userDao.findlikeUser(page, userName);
	}
}
