package cn.erp.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

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
	
}
