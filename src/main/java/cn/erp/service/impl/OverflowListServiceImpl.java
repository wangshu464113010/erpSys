package cn.erp.service.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import cn.erp.dao.OverflowListDao;
import cn.erp.dao.UserDao;
import cn.erp.dao.impl.OverflowListDaoImpl;
import cn.erp.dao.impl.UserDaoImpl;
import cn.erp.domain.OverflowList;
import cn.erp.service.OverflowListService;

public class OverflowListServiceImpl implements OverflowListService{
	
	private OverflowListDao overflowListDao = new OverflowListDaoImpl();
	private UserDao userDao=new UserDaoImpl();
	@Override
	public List<OverflowList> findAll(Date bOverflowDate, Date eOverflowDate) throws SQLException {
		List<OverflowList> list = overflowListDao.find(bOverflowDate, eOverflowDate);
		for (OverflowList overflowList : list) {
			Integer user_id = overflowList.getUser_id();
			overflowList.setUser(userDao.findOne(user_id));
		}
		return list;
	}

}
