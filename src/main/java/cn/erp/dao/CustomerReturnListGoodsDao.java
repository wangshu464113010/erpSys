package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.CustomerReturnListGoods;

public interface CustomerReturnListGoodsDao {
	List<CustomerReturnListGoods> findByCustomerReturnListId(Integer customerReturnListId,Integer type_id,String codeOrName) throws SQLException;

}
