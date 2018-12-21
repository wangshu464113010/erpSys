package cn.erp.service.impl;

import java.sql.SQLException;

import cn.erp.dao.DamageDao;
import cn.erp.dao.DamagelistDao;
import cn.erp.dao.impl.DamageDaoImpl;
import cn.erp.dao.impl.DamagelistDaoImpl;
import cn.erp.domain.Damagelist;
import cn.erp.service.DamagelistService;

public class DamagelistServiceImpl implements DamagelistService{
	
	    private DamagelistDao damagelistdao=new DamagelistDaoImpl();
	@Override
	public void insertDamagelist(Damagelist damagelist) throws SQLException {
		// TODO Auto-generated method stub
		damagelistdao.insertDamagelist(damagelist);
	}

}
