package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.List;

import cn.erp.dao.GoodsDao;
import cn.erp.dao.GoodsTypeDao;
import cn.erp.dao.impl.GoodsDaoImpl;
import cn.erp.dao.impl.GoodsTypeDaoImpl;
import cn.erp.domain.Goods;
import cn.erp.service.GoodsService;

public class GoodsServiceImpl implements GoodsService {

	private GoodsDao goodsDao = new GoodsDaoImpl();
	private GoodsTypeDao goodsTypeDao = new GoodsTypeDaoImpl();
	
	@Override
	public List<Goods> findAll() throws SQLException {
		List<Goods> list = goodsDao.findAll();
		for (Goods goods : list) {
			Integer type_id = goods.getType_id();
			goods.setType(goodsTypeDao.findOne(type_id));
		}
		return list;
	}

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		return goodsDao.count();
	}

}
