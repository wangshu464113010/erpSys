package cn.erp.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import cn.erp.dao.LogDao;
import cn.erp.domain.Log;
import cn.erp.utils.C3P0Util;

public class LogDaoImpl implements LogDao{

	@Override
	public void insertlog(Log log) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql="insert into t_log (id,content,time,type,user_id) values (?,?,?,?,?)";
		int i  =  qr.update(sql,log.getId(),log.getContent(),log.getTime(),log.getType(),log.getUserid());
	}

}
