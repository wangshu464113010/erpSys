package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.List;

import cn.erp.dao.CustomerDao;
import cn.erp.dao.CustomerReturnListDao;
import cn.erp.dao.LogDao;
import cn.erp.dao.UserDao;
import cn.erp.dao.impl.CustomerDaoImpl;
import cn.erp.dao.impl.CustomerReturnListDaoImpl;
import cn.erp.dao.impl.LogDaoImpl;
import cn.erp.dao.impl.UserDaoImpl;
import cn.erp.domain.Customer;
import cn.erp.domain.CustomerReturnList;
import cn.erp.domain.CustomerReturnListGoods;
import cn.erp.domain.Log;
import cn.erp.domain.User;
import cn.erp.service.CustomerReturnListService;

public class CustomerReturnListServiceImpl implements CustomerReturnListService{
	private CustomerReturnListDao customerReturnListDao = new CustomerReturnListDaoImpl();
	private CustomerDao customerDao = new CustomerDaoImpl();
	private UserDao userDao = new UserDaoImpl();
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
}
