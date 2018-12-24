package cn.erp.utils;

import java.sql.SQLException;
import java.util.Date;

import cn.erp.domain.Log;
import cn.erp.service.LogService;
import cn.erp.service.impl.LogServiceImpl;

public class LogUtils {
	private static LogService logService = new LogServiceImpl();
	public static void insertLog(String content,String type,Integer userId) throws SQLException {
		Log log = new Log();
		log.setContent(content);
		log.setTime(new Date());
		log.setType(type);
		log.setUserid(userId);
		logService.insertlog(log);
	}
}
