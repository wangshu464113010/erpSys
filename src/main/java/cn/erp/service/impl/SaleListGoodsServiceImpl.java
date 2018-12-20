package cn.erp.service.impl;

import java.sql.SQLException;

import cn.erp.dao.SaleListGoodsDao;
import cn.erp.dao.impl.SaleListGoodsDaoImpl;
import cn.erp.domain.SaleListGoods;
import cn.erp.service.SaleListGoodsService;

public class SaleListGoodsServiceImpl implements SaleListGoodsService{
	
	private SaleListGoodsDao saleListGoodsDao = new  SaleListGoodsDaoImpl();

	@Override
	public SaleListGoods findSaleListGoodsByGoods_Id(int goods_id) throws SQLException {
		return saleListGoodsDao.findSaleListGoodsByGoods_Id(goods_id);
	}

	@Override
	public int insertSaleListGoods(SaleListGoods saleListGoods) throws SQLException {
		return saleListGoodsDao.insertSaleListGoods(saleListGoods);
	}

}
