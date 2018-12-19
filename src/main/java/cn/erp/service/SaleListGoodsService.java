package cn.erp.service;

import java.sql.SQLException;

import cn.erp.domain.SaleListGoods;

public interface SaleListGoodsService {
	public SaleListGoods findSaleListGoodsByGoods_Id(int goods_id) throws SQLException;
}
