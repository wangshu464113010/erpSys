package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cn.erp.dao.CustomerDao;
import cn.erp.dao.CustomerRetrunListGoodsDao;
import cn.erp.dao.CustomerReturnListDao;
import cn.erp.dao.CustomerReturnListGoodsDao;
import cn.erp.dao.GoodsTypeDao;
import cn.erp.dao.LogDao;
import cn.erp.dao.UserDao;
import cn.erp.dao.impl.CustomerDaoImpl;
import cn.erp.dao.impl.CustomerRetrunListGoodsDaoImpl;
import cn.erp.dao.impl.CustomerReturnListDaoImpl;
import cn.erp.dao.impl.CustomerReturnListGoodsDaoImpl;
import cn.erp.dao.impl.GoodsTypeDaoImpl;
import cn.erp.dao.impl.LogDaoImpl;
import cn.erp.dao.impl.UserDaoImpl;
import cn.erp.domain.Customer;
import cn.erp.domain.CustomerReturnList;
import cn.erp.domain.CustomerReturnListCount;
import cn.erp.domain.CustomerReturnListGoods;
import cn.erp.domain.Log;
import cn.erp.domain.User;
import cn.erp.service.CustomerReturnListService;

public class CustomerReturnListServiceImpl implements CustomerReturnListService{
	private CustomerReturnListDao customerReturnListDao = new CustomerReturnListDaoImpl();
	private CustomerDao customerDao = new CustomerDaoImpl();
	private UserDao userDao = new UserDaoImpl();
	private CustomerReturnListGoodsDao customerReturnListGoodsDao = new CustomerReturnListGoodsDaoImpl();
	private GoodsTypeDao goodsTypeDao = new GoodsTypeDaoImpl();
	
	@Override
	public int insertCustomerRetrunList(Double amount_paid, Double amount_payable, String customer_return_date,
			String customer_return_number, String remarks, Integer state, Integer customer_id) throws SQLException {
		// TODO Auto-generated method stub
		return customerReturnListDao.insertCustomerRetrunList(amount_paid, amount_payable, customer_return_date,customer_return_number,remarks, state, customer_id);
	}
	private LogDao logDao=new  LogDaoImpl();
	
	@Override
	public List<CustomerReturnList> findCustomerReturnListAll(String customer_return_number, Integer customer_id,
			Integer state, String bCustomerReturnDate,String eCustomerReturnDate) throws SQLException {
		List<CustomerReturnList> list = customerReturnListDao.findCustomerReturnListAll(customer_return_number, customer_id, state,
				bCustomerReturnDate,eCustomerReturnDate);
		for (CustomerReturnList customerReturnList : list) {
			Customer customer = customerDao.findById(customerReturnList.getCustomer_id());
			User user = userDao.findUserById(customerReturnList.getUser_id());
			customerReturnList.setCustomer(customer);
			customerReturnList.setUser(user);
		}
		return list;
	}
	@Override
	public List<CustomerReturnListGoods> findCustomerReturnListGoodsAll(Integer customer_return_list_id)
			throws SQLException {
		List<CustomerReturnListGoods> list = customerReturnListDao.findCustomerReturnListGoodsAll(customer_return_list_id);
		return list;
	}
	
		
	public int deleteById(int customer_return_list_id) throws SQLException {
		return customerReturnListDao.deleteById(customer_return_list_id);
	}
		
	@Override
	public List<CustomerReturnListCount> findListCount(String bCustomerReturnDate, String eCustomerReturnDate,
			Integer type_id, String codeOrName) throws SQLException {
		
		List<CustomerReturnListCount> list = customerReturnListDao.findListCount(bCustomerReturnDate,eCustomerReturnDate);
		for (CustomerReturnListCount customerReturnListCount : list) {
			
			customerReturnListCount.setCustomer(customerDao.findById(customerReturnListCount.getCustomer_id()));
			
			customerReturnListCount.setUser(userDao.findUserById(customerReturnListCount.getUser_id()));
			
			List<CustomerReturnListGoods> list2 = customerReturnListGoodsDao.findByCustomerReturnListId(customerReturnListCount.getId(),type_id,codeOrName);
			
			for (CustomerReturnListGoods customerReturnListGoods : list2) {
				customerReturnListGoods.setType(goodsTypeDao.findOne(customerReturnListGoods.getType_id()));
			}
			customerReturnListCount.setCustomer_return_list_goods_list(list2);
		}
//		List<CustomerReturnListCount> list = new ArrayList<>();
//		List<CustomerReturnList> list1 = customerReturnListDao.findListCount(bCustomerReturnDate,eCustomerReturnDate);
//		for (CustomerReturnList customerReturnList : list1) {
//			List<CustomerReturnListGoods> list2 = customerReturnListGoodsDao.findByCustomerReturnListId(customerReturnList.getId(),type_id,codeOrName);
//			for (CustomerReturnListGoods customerReturnListGoods : list2) {
//				CustomerReturnListCount customerReturnListCount = new CustomerReturnListCount();
//				customerReturnListCount.setId(customerReturnList.getId());
//				customerReturnListCount.setAmount_payable(customerReturnList.getAmount_payable());
//				customerReturnListCount.setAmount_paid(customerReturnList.getAmount_paid());
//				customerReturnListCount.setCustomer_return_date(customerReturnList.getCustomer_return_date());
//				customerReturnListCount.setCustomer_return_number(customerReturnList.getCustomer_return_number());
//				customerReturnListCount.setRemarks(customerReturnList.getRemarks());
//				customerReturnListCount.setState(customerReturnList.getState());
//				customerReturnListCount.setUser_id(customerReturnList.getUser_id());
//				customerReturnListCount.setCustomer_id(customerReturnList.getCustomer_id());
//				customerReturnListCount.setCustomer(customerDao.findById(customerReturnList.getCustomer_id()));
//				customerReturnListCount.setUser(userDao.findUserById(customerReturnList.getUser_id()));
////				List<CustomerReturnListGoods> list3 = new ArrayList<>();
////				list3.add(customerReturnListGoods);
//				customerReturnListCount.setCustomer_return_list_goods_list(list2);
//				customerReturnListGoods.setType(goodsTypeDao.findOne(customerReturnListGoods.getType_id()));
//				list.add(customerReturnListCount);
//			}
//		}
		return list;
	}
}
