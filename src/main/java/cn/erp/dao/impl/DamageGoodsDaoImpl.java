package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.DamageGoodsDao;
import cn.erp.dao.GoodsDao;
import cn.erp.dao.GoodsTypeDao;
import cn.erp.domain.DamageList;
import cn.erp.domain.DamageListGoods;
import cn.erp.domain.Goods;
import cn.erp.domain.GoodsType;
import cn.erp.utils.C3P0Util;

public class DamageGoodsDaoImpl implements DamageGoodsDao{
	
	@Override
	public List<DamageListGoods> findById(int damage_list_id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_damage_list_goods where id=?";
		List<DamageListGoods> list =  qr.query(sql,new BeanListHandler<DamageListGoods>(DamageListGoods.class),damage_list_id);
		return list;
	}

	@Override
	public DamageListGoods findOne(int damage_list_id) throws SQLException {
		
			QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
			String sql = "select * from t_goodstype where id = ?";
			return qr.query(sql, new BeanHandler<DamageListGoods>(DamageListGoods.class),damage_list_id);
		
	}

}
