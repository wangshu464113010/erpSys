package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.List;

import cn.erp.dao.UserDao;
import cn.erp.dao.impl.UserDaoImpl;
import cn.erp.domain.User;
import cn.erp.domain.User_Role;
import cn.erp.service.UserService;

public class UserServiceImpl implements UserService{

	private UserDao userDao=new UserDaoImpl();
	
	@Override
	public User login(String user_name, String password)throws SQLException {
		return this.userDao.findByNameAndPassword(user_name,password);
	}

	@Override
	public List<User_Role> findAllUserRoleByUserId(Integer id) throws SQLException {
		return this.userDao.findAllUserRoleByUserId(id);
	}

}
