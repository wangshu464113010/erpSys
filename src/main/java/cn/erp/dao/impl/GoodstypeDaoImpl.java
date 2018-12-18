package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.GoodstypeDao;
import cn.erp.domain.Goodstype;
import cn.erp.utils.C3P0Util;

public class GoodstypeDaoImpl implements GoodstypeDao{
	

	@Override
	public Goodstype findByPid(int pid) throws SQLException {
		String sql = "select * from t_goodstype where id=?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql,new BeanHandler<Goodstype>(Goodstype.class),pid);
	}

	@Override
	public List<Goodstype> findAllByPid(int pid) throws SQLException {
		String sql = "select * from t_goodstype where id=?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql,new BeanListHandler<Goodstype>(Goodstype.class),pid);
	}


}
