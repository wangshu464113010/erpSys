package cn.erp.service.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import cn.erp.dao.LogDao;
import cn.erp.dao.UserDao;
import cn.erp.dao.impl.LogDaoImpl;
import cn.erp.dao.impl.UserDaoImpl;
import cn.erp.domain.Log;
import cn.erp.domain.User;
import cn.erp.service.LogService;

public class LogServiceImpl implements LogService{
	private UserDao userDao = new UserDaoImpl();
	private LogDao logDao = new LogDaoImpl();
	@Override
	public void insertlog(Log log) throws SQLException {
		// TODO Auto-generated method stub
		logDao.insertlog(log);
	}
	@Override
	public List<Log> findByAll(String type, String btime, String etime, int page, int rows, String userTrueName)
			throws SQLException, ParseException {
		List<Log> list = new ArrayList<>();
		if(userTrueName==null || userTrueName.equals("")) {
//			List<User> userList = userDao.findAll();
//			for (User user : userList) {
//				Integer user_id = user.getId();
				List<Log> loglist = logDao.findAll(type, btime, etime, page, rows);
				list.addAll(loglist);
//			}
		}else {
			list=logDao.findAll(type, btime, etime, page, rows);
		}
		for (Log log : list) {
			int user_id = log.getUser_id();
			User user = userDao.findOne(user_id);
			log.setUser(user);
		}
		return list;
	}
	@Override
	public List<Log> findAll(String type, String btime, String etime) throws SQLException, ParseException {
		List<Log> list = logDao.findAll(type, btime, etime);
		return list;
	}



	
	

}
