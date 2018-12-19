package cn.erp.service.impl;

import java.sql.SQLException;

import cn.erp.dao.UserDao;
import cn.erp.dao.impl.UserDaoImpl;
import cn.erp.domain.User;
import cn.erp.service.UserService;

public class UserServiceImpl implements UserService{
	
	private UserDao userDao=new UserDaoImpl();

	@Override
	public User login(String user_name, String password) throws SQLException {
		return null;
	}

}
