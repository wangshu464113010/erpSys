package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.SaleListGoods;

public interface SaleListGoodsDao {
	
	public SaleListGoods findSaleListGoodsByGoods_Id(int goods_id) throws SQLException;
	
	public int deleteBySaleListId(int saleListId) throws SQLException;
	
	public int insertSaleListGoods(SaleListGoods saleListGoods) throws SQLException;
	
	public List<SaleListGoods> selectBySaleListId(Integer saleListId) throws SQLException;
	
}
