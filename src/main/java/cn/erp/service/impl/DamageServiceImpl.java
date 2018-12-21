package cn.erp.service.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import cn.erp.dao.DamageDao;
import cn.erp.dao.DamageGoodsDao;
import cn.erp.dao.UserDao;
import cn.erp.dao.impl.DamageDaoImpl;
import cn.erp.dao.impl.DamageGoodsDaoImpl;
import cn.erp.dao.impl.UserDaoImpl;
import cn.erp.domain.DamageList;
import cn.erp.service.DamageService;

public class DamageServiceImpl implements DamageService{
	
	private DamageDao damageDao=new DamageDaoImpl();
	private UserDao userDao=new UserDaoImpl();

	@Override
	public List<DamageList> findAll(Date bDamageDate,Date eDamageDate)
			throws SQLException, ParseException {
		List<DamageList> list = damageDao.find(bDamageDate, eDamageDate);
		for (DamageList damageList : list) {
			Integer user_id=damageList.getUser_id();
			damageList.setUser(userDao.findOne(user_id));
		}
		return list;
	}

	
}
