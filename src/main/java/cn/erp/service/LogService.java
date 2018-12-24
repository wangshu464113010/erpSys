package cn.erp.service;

import java.sql.SQLException;

import cn.erp.domain.Log;

public interface LogService {
	public void insertlog(Log log) throws SQLException;//向log中添加数据
}
