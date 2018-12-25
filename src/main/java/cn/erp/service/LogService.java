package cn.erp.service;

import java.sql.SQLException;

import cn.erp.domain.Log;

import java.text.ParseException;
import java.util.List;

import cn.erp.domain.Log;

public interface LogService {
	public List<Log> findByAll(String type,String btime,String etime,int page,int rows,String userTrueName) throws SQLException, ParseException;
	public void insertlog(Log log) throws SQLException;//向log中添加数据
	public List<Log> findAll(String type,String btime,String etime) throws SQLException, ParseException;
}
