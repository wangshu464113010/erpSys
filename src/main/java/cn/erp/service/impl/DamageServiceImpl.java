package cn.erp.service.impl;

import java.sql.SQLException;

import cn.erp.dao.DamageDao;
import cn.erp.dao.impl.DamageDaoImpl;
import cn.erp.domain.Damaggoods;
import cn.erp.service.DamageService;

public class DamageServiceImpl implements DamageService{
	
	private DamageDao damageDao = new DamageDaoImpl();
	
	@Override
	public void insertdamage(Damaggoods damaggoods) throws SQLException {
		damageDao.insertdamage(damaggoods);
		
	}

}
