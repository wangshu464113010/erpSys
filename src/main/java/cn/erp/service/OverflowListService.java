package cn.erp.service;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import cn.erp.domain.OverflowList;

public interface OverflowListService {
	public List<OverflowList> findAll(Date bOverflowDate,Date eOverflowDate) throws SQLException;

}
