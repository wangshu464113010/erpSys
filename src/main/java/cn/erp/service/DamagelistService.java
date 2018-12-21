package cn.erp.service;

import java.sql.SQLException;

import cn.erp.domain.DamageList;


public interface DamagelistService {
	public void insertDamagelist(DamageList damagelist)throws SQLException;
}
