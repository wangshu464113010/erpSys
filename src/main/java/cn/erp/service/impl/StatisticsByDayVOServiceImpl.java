package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import cn.erp.dao.StatisticsByDayVODao;
import cn.erp.dao.impl.StatisticsByDayVODaoImpl;
import cn.erp.domain.StatisticsByDayVO;
import cn.erp.service.StatisticsByDayVOService;

public class StatisticsByDayVOServiceImpl implements StatisticsByDayVOService {
	private StatisticsByDayVODao statisticsByDayVODao = new StatisticsByDayVODaoImpl();
	@Override
	public List<StatisticsByDayVO> findAll(Date startDate, Date endDate) throws SQLException {
		List<StatisticsByDayVO> list = statisticsByDayVODao.findAll(startDate, endDate);
//		for (StatisticsByDayVO statisticsByDayVO : list) {
//			if(statisticsByDayVO.getAmountCost().equals(null)){
//				statisticsByDayVO.setAmountCost(0.0);
//			}
//			if(statisticsByDayVO.getAmountProfit().equals(null)){
//				statisticsByDayVO.setAmountProfit(0.0);
//			}
//			if(statisticsByDayVO.getAmountSale().equals(null)){
//				statisticsByDayVO.setAmountSale(0.0);
//			}
//		}
		return list;
	}

}
