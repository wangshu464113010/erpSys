package cn.erp.service;

import java.sql.SQLException;

import cn.erp.domain.CustomerReturnListGoods;

public interface CustomerReturnListGoodsService {
	public int insertCustomerRetrunListGoods(CustomerReturnListGoods customerReturnListGoods) throws SQLException;
}