package cn.erp.dao;

import java.sql.SQLException;

import cn.erp.domain.Damagelist;

public interface DamagelistDao {
	//向t_damage_list里面插入数据
	public void insertDamagelist(Damagelist damagelist)throws SQLException;
	public String getMaxId(Damagelist damagelist) throws SQLException;
}
	