package cn.erp.dao;

import java.sql.SQLException;

import cn.erp.domain.Log;

  
   // public List<log>(Log log);
import java.text.ParseException;
import java.util.List;

import cn.erp.domain.Log;

public interface LogDao {
	public List<Log> findAll(String type,String btime,String etime,int page,int rows,int user_id) throws SQLException, ParseException;
	
//	public List<Log> findAll() throws SQLException;
	
	//public int count() throws SQLException;

	List<Log> findAll(String type, String btime, String etime, int user_id) throws SQLException, ParseException;
	  public void insertlog(Log log) throws SQLException;//记录日志
	
	
}
