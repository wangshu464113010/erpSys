package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.List;

import cn.erp.dao.GoodsTypeDao;
import cn.erp.dao.ListAlarmDao;
import cn.erp.dao.impl.GoodsTypeDaoImpl;
import cn.erp.dao.impl.ListAlarmDaoImpl;
import cn.erp.domain.Goods;
import cn.erp.service.ListAlarmService;

public class ListAlarmServiceImpl implements ListAlarmService{
	private ListAlarmDao listAlarmDao = new ListAlarmDaoImpl();
	private GoodsTypeDao goodsTypeDao = new GoodsTypeDaoImpl();
	@Override
	public List<Goods> findAll() throws SQLException {
		List<Goods> list = listAlarmDao.findAll();
		for (Goods goods : list) {
			Integer type_id = goods.getType_id();
			goods.setType(goodsTypeDao.findOne(type_id));
		}
		return list;
	
	}

}
