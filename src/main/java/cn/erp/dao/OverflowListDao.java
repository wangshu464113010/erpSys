package cn.erp.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import cn.erp.domain.OverflowList;

public interface OverflowListDao {
	public List<OverflowList> find(Date bOverflowDate, Date eOverflowDate) throws SQLException;
	
	public OverflowList findone(int id)throws SQLException;
	
	public List<OverflowList> findByOverflowNumber(String overflow_number) throws SQLException;
	
	public List<OverflowList> findById(int id) throws SQLException;
}
