package cn.erp.service.impl;

import java.sql.SQLException;

import cn.erp.dao.CustomerRetrunListGoodsDao;
import cn.erp.dao.SaleListGoodsDao;
import cn.erp.dao.impl.CustomerRetrunListGoodsDaoImpl;
import cn.erp.dao.impl.SaleListGoodsDaoImpl;
import cn.erp.domain.CustomerReturnListGoods;
import cn.erp.domain.SaleListGoods;
import cn.erp.service.CustomerReturnListGoodsService;

public class CustomerReturnListGoodsServiceImpl implements CustomerReturnListGoodsService{
	
	private CustomerRetrunListGoodsDao customerRetrunListGoodsDao = new CustomerRetrunListGoodsDaoImpl();
	@Override
	public int insertCustomerRetrunListGoods(CustomerReturnListGoods customerReturnListGoods) throws SQLException {
		// TODO Auto-generated method stub
		return customerRetrunListGoodsDao.insertCustomerRetrunListGoods(customerReturnListGoods);
	}
}
