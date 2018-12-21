package cn.erp.dao.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.OverflowListDao;
import cn.erp.domain.OverflowList;
import cn.erp.utils.C3P0Util;

public class OverflowListDaoImpl implements OverflowListDao{

	@Override
	public List<OverflowList> find(Date bOverflowDate, Date eOverflowDate) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_overflow_list where overflow_date between  ? and  ? ";
		List<OverflowList> list = qr.query(sql, new BeanListHandler<OverflowList>(OverflowList.class),bOverflowDate,eOverflowDate);
		return list;
	}

	@Override
	public OverflowList findone(int id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_overflow_list where id = ?";
		return qr.query(sql, new BeanHandler<OverflowList>(OverflowList.class),id);
	}

	@Override
	public List<OverflowList> findByOverflowNumber(String overflow_number) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_overflow_list where overflow_number= ? ";
		List<OverflowList> list = qr.query(sql, new BeanListHandler<OverflowList>(OverflowList.class),overflow_number);
		return list;
	}

	@Override
	public List<OverflowList> findById(int id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_overflow_list  where id = ?";
		List<OverflowList> list = qr.query(sql, new BeanListHandler<OverflowList>(OverflowList.class),id);
		return list;
	}

}
