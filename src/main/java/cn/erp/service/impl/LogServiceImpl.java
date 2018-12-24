package cn.erp.service.impl;

import java.sql.SQLException;

import cn.erp.dao.LogDao;
import cn.erp.dao.impl.LogDaoImpl;
import cn.erp.domain.Log;
import cn.erp.service.LogService;

public class LogServiceImpl implements LogService{
	
	private LogDao logdao=new LogDaoImpl();
	@Override
	public void insertlog(Log log) throws SQLException {
		// TODO Auto-generated method stub
		logdao.insertlog(log);
	}

}
