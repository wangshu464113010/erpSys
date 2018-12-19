package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.SaleListGoodsDao;
import cn.erp.domain.SaleListGoods;
import cn.erp.utils.C3P0Util;

public class SaleListGoodsDaoImpl implements SaleListGoodsDao{

	@Override
	public SaleListGoods findSaleListGoodsByGoods_Id(int goods_id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_sale_list_goods where goods_id=?";
		SaleListGoods saleListGoods = qr.query(sql,new BeanHandler<SaleListGoods>(SaleListGoods.class),goods_id);
		return saleListGoods;
	}

	@Override
	public int deleteBySaleListId(int saleListId) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "delete from t_sale_list_goods where sale_list_id=?";
		int i = qr.update(sql,saleListId);
		return i;
	}
	
}
