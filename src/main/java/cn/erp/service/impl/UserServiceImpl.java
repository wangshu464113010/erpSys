package cn.erp.service.impl;

import java.sql.SQLException;

import cn.erp.dao.UserDao;
import cn.erp.dao.impl.UserDaoImpl;
import cn.erp.domain.User;
import cn.erp.service.UserService;

public class UserServiceImpl implements UserService{
	private UserDao userDao=new UserDaoImpl();
	@Override
	public User findByUser_id(int id) throws SQLException {
		return userDao.findOne(id);
	}

}
