package cn.erp.service;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import cn.erp.domain.DamageList;

public interface DamageService {
	public List<DamageList> findAll(Date bDamageDate,Date eDamageDate) throws SQLException, ParseException;
}
