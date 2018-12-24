package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.LogVODao;
import cn.erp.domain.IntegerDO;
import cn.erp.domain.LogVO;
import cn.erp.domain.Page;
import cn.erp.utils.C3P0Util;

public class LogVODaoImpl implements LogVODao{

	@Override
	public List<LogVO> FindAllLogVO(Page page) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql="select l.id id,l.type type,t.true_name true_name,l.time time,l.content content,t.id userId"
				+ " from t_log l,t_user t where l.user_id=t.id limit ?,?";
		return qr.query(sql, new BeanListHandler<LogVO>(LogVO.class),
				(page.getPageNow()-1)*page.getSize(),page.getSize());
	}

	@Override
	public int count() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql="select count(*) as count from t_log";
		IntegerDO query = qr.query(sql, new BeanHandler<IntegerDO>(IntegerDO.class));
		return query.getCount();
	}

}

