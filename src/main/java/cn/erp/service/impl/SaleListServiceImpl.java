package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.erp.dao.CustomerDao;
import cn.erp.dao.GoodsTypeDao;
import cn.erp.dao.SaleListDao;
import cn.erp.dao.SaleListGoodsDao;
import cn.erp.dao.UserDao;
import cn.erp.dao.impl.CustomerDaoImpl;
import cn.erp.dao.impl.GoodsTypeDaoImpl;
import cn.erp.dao.impl.SaleListDaoImpl;
import cn.erp.dao.impl.SaleListGoodsDaoImpl;
import cn.erp.dao.impl.UserDaoImpl;
import cn.erp.domain.Customer;
import cn.erp.domain.GoodsType;
import cn.erp.domain.SaleList;
import cn.erp.domain.SaleListGoods;
import cn.erp.domain.User;
import cn.erp.service.SaleListService;

public class SaleListServiceImpl implements SaleListService{

	private SaleListDao saleListDao = new SaleListDaoImpl();
	private CustomerDao customerDao = new CustomerDaoImpl();
	private UserDao userDao = new UserDaoImpl();
	private GoodsTypeDao goodsTypeDao = new GoodsTypeDaoImpl();
	private SaleListGoodsDao saleListGoodsDao = new SaleListGoodsDaoImpl();

	@Override
	public int addSaleList(SaleList saleList) throws SQLException {
		return saleListDao.insertSaleList(saleList);
	}

	@Override
	public List<SaleList> selectByCondition(String sale_number,Integer customer_id,Integer state,String bSaleDate,String eSaleDate) throws SQLException {
		List<SaleList> list = saleListDao.selectByCondition(sale_number,customer_id,state,bSaleDate,eSaleDate);
		for (SaleList saleList : list) {
			Customer customer = customerDao.findById(saleList.getCustomer_id());
			saleList.setCustomer(customer);
			User user = userDao.findUserById(saleList.getUser_id());
			saleList.setUser(user);
		}
		return list;
	}

	@Override
	public List<SaleListGoods> findAllListGoodsById(Integer id) throws SQLException {
		List<SaleListGoods> list=new ArrayList<>();
		List<SaleListGoods> saleListGoods = saleListDao.findAllBySaleListId(id);
		if(saleListGoods.size()==0){
			return list;
		}
		SaleList saleList=saleListDao.findById(id);
		Customer customer = customerDao.findById(saleList.getCustomer_id());
		User user = userDao.findUserById(saleList.getUser_id());
		GoodsType goodsType=new GoodsType();
		if(saleListGoods.size()>0){
			goodsType = goodsTypeDao.findOne(saleListGoods.get(0).getType_id());
		}
		saleList.setCustomer(customer);
		saleList.setUser(user);
		for (SaleListGoods saleListGoods2 : saleListGoods) {
			saleListGoods2.setSaleList(saleList);
			saleListGoods2.setType(goodsType);
			list.add(saleListGoods2);
		}
		return list;
	}

	@Override
	public int deleteById(int id) throws SQLException {
		saleListGoodsDao.deleteBySaleListId(id);
		int i = saleListDao.deleteById(id);
		return i;
	}
	

}
