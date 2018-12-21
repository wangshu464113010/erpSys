package cn.erp.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import cn.erp.domain.DamageList;
import cn.erp.domain.OverflowList;

public interface DamageDao {
	public List<DamageList> find(int damageListId) throws SQLException;
	
	public List<DamageList> find(Date bDamageDate, Date eDamageDate) throws SQLException, ParseException;

	public List<DamageList> findByOverflowNumber(String damage_number) throws SQLException;
	
	public DamageList findone(int id)throws SQLException;
}
