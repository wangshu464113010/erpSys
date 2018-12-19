package cn.erp.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import cn.erp.dao.OverlistDao;
import cn.erp.domain.Overlist;
import cn.erp.utils.C3P0Util;

public class OverlistDaoImpl implements OverlistDao {

	@Override
	public void insertoverlist(Overlist overlist) throws SQLException {
		 QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		 String sql="insert into t_overflow_list(overflow_date,overflow_number,remarks,user_id) values(?,?,?,?);";
		// System.out.println("____________"+overlist.get);
		 
		 int i=qr.update(sql,overlist.getOverflow_date(),overlist.getOverflow_number(),overlist.getRemarks(),overlist.getUser_id());
		
	}

}
