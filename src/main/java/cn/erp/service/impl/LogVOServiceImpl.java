package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.List;

import cn.erp.dao.LogVODao;
import cn.erp.dao.UserDao;
import cn.erp.dao.impl.LogVODaoImpl;
import cn.erp.dao.impl.UserDaoImpl;
import cn.erp.domain.LogVO;
import cn.erp.domain.Page;
import cn.erp.domain.User;
import cn.erp.service.LogVOService;

public class LogVOServiceImpl implements LogVOService{
	private LogVODao logVODao=new LogVODaoImpl();
	private UserDao userDao = new UserDaoImpl();
	@Override
	public List<LogVO> FindAllLogVO(Page page) throws SQLException {
		// TODO Auto-generated method stub
		List<LogVO> list = logVODao.FindAllLogVO(page);
		for (LogVO log : list) {
			Integer userId = log.getUserId();
			User user = userDao.findUserById(userId);
			log.setUser(user);
		}
		 return list;
	}
	@Override
	public int count() throws SQLException {		
		return logVODao.count();
	}

}
