package cn.erp.dao;

import java.sql.SQLException;

import cn.erp.domain.DamageList;

public interface DamagelistDao {
	//向t_damage_list里面插入数据
	public void insertDamagelist(DamageList damagelist)throws SQLException;
	public String getMaxId(DamageList damagelist) throws SQLException;
}
	