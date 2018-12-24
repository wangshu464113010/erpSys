package cn.erp.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import cn.erp.domain.Log;

public interface LogService {
	public List<Log> findByAll(String type,String btime,String etime,int page,int rows,String userTrueName) throws SQLException, ParseException;
}
