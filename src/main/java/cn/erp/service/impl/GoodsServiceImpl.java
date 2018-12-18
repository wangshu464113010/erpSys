package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.List;

import cn.erp.dao.GoodsDao;
import cn.erp.dao.impl.GoodsDaoImpl;
import cn.erp.domain.Goods;
import cn.erp.service.GoodsService;

public class GoodsServiceImpl implements GoodsService {
	
	private GoodsDao goodsDao = new GoodsDaoImpl();

	@Override
	public List<Goods> findAll() throws SQLException {
		return goodsDao.findAll();
	}
	

}
