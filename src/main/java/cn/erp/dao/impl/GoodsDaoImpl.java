package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.GoodsDao;
import cn.erp.domain.Goods;
import cn.erp.utils.C3P0Util;

public class GoodsDaoImpl implements GoodsDao{
	
	public List<Goods> findAll() throws SQLException {
		String sql = "select * from t_goods";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql,new BeanListHandler<Goods>(Goods.class));
	}

}
