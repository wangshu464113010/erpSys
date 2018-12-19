package cn.erp.dao;

import java.sql.SQLException;

import cn.erp.domain.SaleListGoods;

public interface SaleListGoodsDao {
	
	public SaleListGoods findSaleListGoodsByGoods_Id(int goods_id) throws SQLException;
}
