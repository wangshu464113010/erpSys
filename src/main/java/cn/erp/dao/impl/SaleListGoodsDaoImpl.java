package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.SaleListGoodsDao;
import cn.erp.domain.CustomerReturnListGoods;
import cn.erp.domain.SaleList;
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

	@Override
	public int insertSaleListGoods(SaleListGoods saleListGoods) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "insert into t_sale_list_goods values(default,?,?,?,?,?,?,?,?,?,?)";
		int i = qr.update(sql,saleListGoods.getCode(),saleListGoods.getModel(),saleListGoods.getName(),saleListGoods.getNum(),saleListGoods.getPrice(),
				saleListGoods.getTotal(),saleListGoods.getUnit(),saleListGoods.getSale_list_id(),saleListGoods.getType_id(),saleListGoods.getGoods_id());
		return i;
	}

	@Override
	public List<SaleListGoods> findBySaleListId(Integer saleListId) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_sale_list_goods where sale_list_id=?";
		return qr.query(sql, new BeanListHandler<SaleListGoods>(SaleListGoods.class),saleListId);
	}
	
}
