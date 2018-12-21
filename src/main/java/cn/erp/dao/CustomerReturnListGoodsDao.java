package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.CustomerReturnListGoods;

public interface CustomerReturnListGoodsDao {
	public List<CustomerReturnListGoods> findByCustomerReturnListId(Integer customerReturnListId) throws SQLException;
}
