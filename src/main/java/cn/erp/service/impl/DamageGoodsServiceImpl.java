package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.erp.dao.DamageDao;
import cn.erp.dao.DamageGoodsDao;
import cn.erp.dao.GoodsTypeDao;
import cn.erp.dao.UserDao;
import cn.erp.dao.impl.DamageDaoImpl;
import cn.erp.dao.impl.DamageGoodsDaoImpl;
import cn.erp.dao.impl.GoodsTypeDaoImpl;
import cn.erp.dao.impl.UserDaoImpl;
import cn.erp.domain.DamageList;
import cn.erp.domain.DamageListGoods;
import cn.erp.service.DamageGoodsService;

public class DamageGoodsServiceImpl implements DamageGoodsService{
	private DamageDao damageDao=new DamageDaoImpl();
	private DamageGoodsDao damageGoodsDao=new DamageGoodsDaoImpl();
	private UserDao userDao=new UserDaoImpl();
	private GoodsTypeDao goodsType=new GoodsTypeDaoImpl();
	
	
	@Override
	public List<DamageListGoods> findBydamage_list_id(int id) throws SQLException {
		List<DamageListGoods> list = new ArrayList<>();
		String damage_number = damageDao.findone(id).getDamage_number();
		List<DamageList> damageList = damageDao.findByOverflowNumber(damage_number);
		for (DamageList damageList2 : damageList) {
			Integer damageListId = damageList2.getId();
			List<DamageListGoods> damageListGoods = damageGoodsDao.findById(damageListId);
			for (DamageListGoods damageListGoods1 : damageListGoods) {
				Integer id1 = damageListGoods1.getDamage_list_id();
				List<DamageList> damageList3 = damageDao.find(id1);
				for (DamageList damageList4 : damageList) {
					Integer user_id = damageList4.getUser_id();
					damageList4.setUser(userDao.findOne(user_id));
				}
				damageListGoods1.setDamageList(damageList3);
			}
			list.addAll(damageListGoods);
		}
		
//		List<DamageListGoods> list=damageGoodsDao.findById(id);
//		for (DamageListGoods damageListGoods : list) {
//			Integer type_id=damageListGoods.getType_id();
//			damageListGoods.setType(goodsType.findOne(type_id));
//			Integer damage_list_id=damageListGoods.getDamage_list_id();
//			List<DamageList> damagelist = damageDao.find(damage_list_id);
//			for (DamageList damageList : damagelist) {
//				Integer user_id=damageList.getUser_id();
//				damageList.setUser(userDao.findOne(user_id));
//			}
//			damageListGoods.setDamageList(damagelist);
//		}
		return list;
	}



	

}
