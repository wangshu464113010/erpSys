package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.CustomerReturnListGoods;

public interface CustomerRetrunListGoodsDao {
	//客户退货单据生成
	public int insertCustomerRetrunListGoods(CustomerReturnListGoods customerReturnListGoods) throws SQLException;
	
	public List<CustomerReturnListGoods> findByCustomerReturnListId(Integer customerReturnListId,Integer type_id,String codeOrName) throws SQLException;
}
