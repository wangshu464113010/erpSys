package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.CustomerReturnListGoodsDao;
import cn.erp.domain.CustomerReturnList;
import cn.erp.domain.CustomerReturnListGoods;
import cn.erp.utils.C3P0Util;

public class CustomerReturnListGoodsDaoImpl implements CustomerReturnListGoodsDao{

	@Override
	public List<CustomerReturnListGoods> findByCustomerReturnListId(Integer customerReturnListId) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_customer_return_list_goods where customer_return_list_id=?";
		return qr.query(sql, new BeanListHandler<CustomerReturnListGoods>(CustomerReturnListGoods.class),customerReturnListId);
	}
	
}
