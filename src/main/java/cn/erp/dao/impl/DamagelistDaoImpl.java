package cn.erp.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.erp.dao.DamagelistDao;
import cn.erp.domain.DamageList;
import cn.erp.utils.C3P0Util;

public class DamagelistDaoImpl implements DamagelistDao{

	@Override
	public void insertDamagelist(DamageList damagelist) throws SQLException {
		// TODO Auto-generated method stub
		 QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		 String sql="insert into t_damage_list(damage_date,damage_number,remarks,user_id) values(?,?,?,?);";
		 int i=qr.update(sql,damagelist.getDamage_date(),damagelist.getDamage_number(),damagelist.getRemarks(),damagelist.getUser_id());
	}
	
	@Override
	public String getMaxId(DamageList damagelist) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select max(id) as id from t_damage_list";
		DamageList query=qr.query(sql, new BeanHandler<DamageList>(DamageList.class));
		return query.getId()+"";
	}
}
