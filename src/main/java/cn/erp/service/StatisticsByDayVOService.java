package cn.erp.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import cn.erp.domain.StatisticsByDayVO;

public interface StatisticsByDayVOService {
	public List<StatisticsByDayVO> findAll(Date startDate,Date endDate) throws SQLException;
}
