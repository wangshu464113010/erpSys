package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.List;

import cn.erp.dao.GoodsunitDao;
import cn.erp.dao.impl.GoodsunitDaoImpl;
import cn.erp.domain.Goodsunit;
import cn.erp.service.GoodsunitService;

public class GoodsunitServiceImpl implements GoodsunitService {
	private GoodsunitDao goodsunitDao = new GoodsunitDaoImpl();
	@Override
	public List<Goodsunit> queryAll() throws SQLException {
		return goodsunitDao.getAll();
	}

	@Override
	public int add(Goodsunit goodsunit) throws SQLException {
		return goodsunitDao.insert(goodsunit);
	}

	@Override
	public int delete(Goodsunit goodsunit) throws SQLException {
		return goodsunitDao.delete(goodsunit);
	}

}
