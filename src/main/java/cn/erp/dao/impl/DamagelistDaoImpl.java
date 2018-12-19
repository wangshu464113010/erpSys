package cn.erp.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import cn.erp.dao.DamagelistDao;
import cn.erp.domain.Damagelist;
import cn.erp.utils.C3P0Util;

public class DamagelistDaoImpl implements DamagelistDao{

	@Override
	public void insertDamagelist(Damagelist damagelist) throws SQLException {
		// TODO Auto-generated method stub
		 QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		 String sql="insert into t_damage_list(damage_date,damage_number,remarks,user_id) values(?,?,?,?);";
		 int i=qr.update(sql,damagelist.getDamage_date(),damagelist.getDamage_number(),damagelist.getRemarks(),damagelist.getUser_id());
	}
}
