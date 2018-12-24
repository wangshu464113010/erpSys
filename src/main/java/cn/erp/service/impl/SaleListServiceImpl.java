package cn.erp.service.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.erp.dao.CustomerDao;
import cn.erp.dao.GoodsDao;
import cn.erp.dao.GoodsTypeDao;
import cn.erp.dao.SaleListDao;
import cn.erp.dao.SaleListGoodsDao;
import cn.erp.dao.UserDao;
import cn.erp.dao.impl.CustomerDaoImpl;
import cn.erp.dao.impl.GoodsDaoImpl;
import cn.erp.dao.impl.GoodsTypeDaoImpl;
import cn.erp.dao.impl.SaleListDaoImpl;
import cn.erp.dao.impl.SaleListGoodsDaoImpl;
import cn.erp.dao.impl.UserDaoImpl;
import cn.erp.domain.CountSale;
import cn.erp.domain.Customer;
import cn.erp.domain.Goods;
import cn.erp.domain.CustomerReturnListCount;
import cn.erp.domain.CustomerReturnListGoods;
import cn.erp.domain.GoodsType;
import cn.erp.domain.SaleList;
import cn.erp.domain.SaleListCount;
import cn.erp.domain.SaleListGoods;
import cn.erp.domain.User;
import cn.erp.service.SaleListService;
import cn.erp.utils.StringUtils;

public class SaleListServiceImpl implements SaleListService{

	private SaleListDao saleListDao = new SaleListDaoImpl();
	private CustomerDao customerDao = new CustomerDaoImpl();
	private UserDao userDao = new UserDaoImpl();
	private GoodsTypeDao goodsTypeDao = new GoodsTypeDaoImpl();
	private SaleListGoodsDao saleListGoodsDao = new SaleListGoodsDaoImpl();
	private GoodsDao goodsDao=new GoodsDaoImpl();

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

	@Override
	public int findByMaxId() throws SQLException {
		 List<SaleList> list = saleListDao.findByMaxId();
		 int id = 0;
		 for (int i = 0; i < list.size(); i++) {
			 int id2 = list.get(i).getId();
			 if(id < id2){
				 id = id2;
			 }
		}
		 return id;
	}

	@Override
	public List<CountSale> findByMonthTj(String begin, String end) throws SQLException {
		List<CountSale> count=new ArrayList<>();
		List<SaleList> list = this.saleListDao.findByDate(begin, end); //查询所有的在该月份下的输数据
		for (SaleList saleList : list) { //对每个数据进行遍历
			CountSale countSale=new CountSale();
			double amoutCost=0;
			List<SaleListGoods> listGoods = this.saleListGoodsDao.selectBySaleListId(saleList.getId());//查找当前数据进价
			for (SaleListGoods saleListGoods : listGoods) {
				Goods goods = this.goodsDao.findGoodsByCode(saleListGoods.getCode());
				double purchasing_price = goods.getPurchasing_price();
				int num = saleListGoods.getNum().intValue();
				amoutCost+=num*purchasing_price; //统计在该sale_list_id下进价的总和
			}
			countSale.setDate(new SimpleDateFormat("yyyy-MM").format(saleList.getSale_date()));
			countSale.setAmountCost(amoutCost);
			count.add(countSale);
		}
		List<String> monthBetween=new ArrayList<>();
		try {
			monthBetween = StringUtils.getMonthBetween(begin, end);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<CountSale> list2=new ArrayList<>(); //将要返回的集合
		for (String string : monthBetween) { //进行遍历，将每月的总和进行统计
			double amoutSale=0;
			double amoutCost=0;
			for (SaleList sale : list) {
				if(string.equals(new SimpleDateFormat("yyyy-MM").format(sale.getSale_date()))){
					amoutSale+=sale.getAmount_paid();
				}
			}
			CountSale countSale=new CountSale();
			countSale.setDate(string);
			countSale.setAmountSale(amoutSale);
			for (CountSale countSale1 : count) {
				if(countSale1.getDate().equals(string)){
					amoutCost+=countSale1.getAmountCost();
				}
			}
			countSale.setAmountCost(amoutCost);
			countSale.setAmountProfit(amoutSale-amoutCost);
			list2.add(countSale);
		}
		return list2;
	}
	
	public int updataState(int id) throws SQLException {
		return saleListDao.updataState(id);
	}
	
	@Override
	public List<SaleListCount> findListCount(String bSaleDate, String eSaleDate, Integer type_id, String codeOrName)
			throws SQLException {
		List<SaleListCount> list = saleListDao.findListCount(bSaleDate, eSaleDate);
		for (SaleListCount saleListCount : list) {
			saleListCount.setCustomer(customerDao.findById(saleListCount.getCustomer_id()));
			saleListCount.setUser(userDao.findUserById(saleListCount.getUser_id()));
			List<SaleListGoods> list2 = saleListGoodsDao.findBySaleListId(saleListCount.getId(),type_id,codeOrName);
			for (SaleListGoods saleListGoods : list2) {
				saleListGoods.setType(goodsTypeDao.findOne(saleListGoods.getType_id()));
			}
			saleListCount.setSale_list_goods_list(list2);
		}
		return list;
	}

	@Override
	public String findSaleNumber() throws SQLException {
		return saleListDao.findSaleNumber();
	}
	

}
