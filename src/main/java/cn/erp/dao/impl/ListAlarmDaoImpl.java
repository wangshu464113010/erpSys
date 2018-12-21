package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.ListAlarmDao;
import cn.erp.domain.Goods;
import cn.erp.utils.C3P0Util;

public class ListAlarmDaoImpl implements ListAlarmDao{

	@Override
	public List<Goods> findAll() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_goods where min_num > inventory_quantity";
		List<Goods> list =  qr.query(sql,new BeanListHandler<Goods>(Goods.class));
		return list;
	}

}
