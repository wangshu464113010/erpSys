package cn.erp.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import cn.erp.domain.StatisticsByDayVO;

public interface StatisticsByDayVODao {
	public List<StatisticsByDayVO> findAll(Date beginDate,Date endDate)throws SQLException;
}
