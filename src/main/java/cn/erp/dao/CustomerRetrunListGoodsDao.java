package cn.erp.dao;

import java.sql.SQLException;

import cn.erp.domain.CustomerReturnListGoods;

public interface CustomerRetrunListGoodsDao {
	//客户退货单据生成
	public int insertCustomerRetrunListGoods(CustomerReturnListGoods customerReturnListGoods) throws SQLException;
}
