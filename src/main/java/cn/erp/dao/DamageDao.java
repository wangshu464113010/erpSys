package cn.erp.dao;

import java.sql.SQLException;

import cn.erp.domain.Damaggoods;

//鎿嶄綔鍟嗗搧鎹熷潖鐨勬帴鍙�
public interface DamageDao{
	
	public void insertdamage(Damaggoods damaggoods) throws SQLException;
}
