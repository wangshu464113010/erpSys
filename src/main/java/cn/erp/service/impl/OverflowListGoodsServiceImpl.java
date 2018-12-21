package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.erp.dao.OverflowListDao;
import cn.erp.dao.OverflowListGoodsDao;
import cn.erp.dao.UserDao;
import cn.erp.dao.impl.OverflowListDaoImpl;
import cn.erp.dao.impl.OverflowListGoodsDaoImpl;
import cn.erp.dao.impl.UserDaoImpl;
import cn.erp.domain.OverflowList;
import cn.erp.domain.OverflowListGoods;
import cn.erp.service.OverflowListGoodsService;

public class OverflowListGoodsServiceImpl implements OverflowListGoodsService{
	private OverflowListDao overflowListDao = new OverflowListDaoImpl();
	private OverflowListGoodsDao overflowListGoodsDao = new OverflowListGoodsDaoImpl();
	private UserDao userDao=new UserDaoImpl();
	@Override
	public List<OverflowListGoods> findAll(int overflowListId) throws SQLException {
		List<OverflowListGoods> list = new ArrayList<>();
		String overflow_number = overflowListDao.findone(overflowListId).getOverflow_number();
		List<OverflowList> overflowList = overflowListDao.findByOverflowNumber(overflow_number);
		for (OverflowList overflowList2 : overflowList) {
			Integer overflowid = overflowList2.getId();
			List<OverflowListGoods> list1 = overflowListGoodsDao.findAll(overflowid);
			for (OverflowListGoods overflowListGoods : list1) {
				Integer id = overflowListGoods.getOverflow_list_id();
				List<OverflowList> overflowlist = overflowListDao.findById(id);
				for (OverflowList overflowList3 : overflowlist) {
					Integer user_id = overflowList3.getUser_id();
					overflowList3.setUser(userDao.findOne(user_id));
				}
				overflowListGoods.setOverflow(overflowlist);
			}
			list.addAll(list1);
			//null.addAll(list1);
		}
		return list;
	}


}
