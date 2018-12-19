package cn.erp.service;

import java.sql.SQLException;

import cn.erp.domain.Damagelist;

public interface DamagelistService {
	public void insertDamagelist(Damagelist damagelist)throws SQLException;
}
