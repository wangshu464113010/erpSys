package cn.erp.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import cn.erp.domain.DamageList;
import cn.erp.domain.Damaggoods;

public interface DamageService {
	public void insertdamage(Damaggoods damaggoods) throws SQLException;
	public List<DamageList> findAll(Date bDamageDate,Date eDamageDate) throws SQLException, ParseException;

}

