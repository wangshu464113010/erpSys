package cn.erp.dao.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.DamageDao;
import cn.erp.domain.DamageList;
import cn.erp.domain.Damaggoods;
import cn.erp.utils.C3P0Util;

public class DamageDaoImpl implements DamageDao{
	@Override
	public void insertdamage(Damaggoods damaggoods) throws SQLException {
		 QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		 String sql = "insert into t_damage_list_goods(code,model,name,num,price,total,unit,type_Id,goods_Id) values(?,?,?,?,?,?,?,?,?)";
			int i  =  qr.update(sql,damaggoods.getCode(),damaggoods.getModel(),damaggoods.getName(),damaggoods.getNum(),
					damaggoods.getPrice(),damaggoods.getTotal(),damaggoods.getUnit(),damaggoods.getType_id(),damaggoods.getGoods_id());
	}

	@Override
	public List<DamageList> find(int damageListId) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_damage_list where id=?";
		List<DamageList> list =  qr.query(sql,new BeanListHandler<DamageList>(DamageList.class),damageListId);
		return list;
	}
	//	@Override
//	public List<DamageList> find(java.sql.Date bDamageDate, java.sql.Date eDamageDate) throws SQLException{
//		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
//		String sql = "select * from t_damage_list where damage_date between '?' and '?'";
//	
//		List<DamageList> list =  qr.query(sql,
//				new BeanListHandler<DamageList>(DamageList.class),
//				bDamageDate
//				,eDamageDate);
//	
//		return list;
//	}
	
	@Override
	public List<DamageList> find(Date bDamageDate,Date eDamageDate) throws SQLException, ParseException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_damage_list where damage_date between  ? and  ? ";
		List<DamageList> list =  qr.query(sql,new BeanListHandler<DamageList>(DamageList.class),bDamageDate,eDamageDate);
		return list;
	}

	@Override
	public List<DamageList> findByOverflowNumber(String damage_number) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_damage_list where damage_number= ? ";
		List<DamageList> list = qr.query(sql, new BeanListHandler<DamageList>(DamageList.class),damage_number);
		return list;
	}

	@Override
	public DamageList findone(int id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_damage_list where id = ?";
		return qr.query(sql, new BeanHandler<DamageList>(DamageList.class),id);
	}
	
}
