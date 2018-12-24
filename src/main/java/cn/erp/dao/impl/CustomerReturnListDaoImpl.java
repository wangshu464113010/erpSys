package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.CustomerReturnListDao;
import cn.erp.domain.CustomerReturnList;
import cn.erp.domain.CustomerReturnListCount;
import cn.erp.domain.CustomerReturnListGoods;
import cn.erp.domain.Goods;
import cn.erp.domain.Purchase;
import cn.erp.domain.Purchase_List;
import cn.erp.domain.SaleList;
import cn.erp.utils.C3P0Util;

public class CustomerReturnListDaoImpl implements CustomerReturnListDao {

	@Override
	public List<CustomerReturnList> findCustomerReturnListAll(String customer_return_number, Integer customer_id,
			Integer state, String bCustomerReturnDate, String eCustomerReturnDate) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "";
		List<CustomerReturnList> list = null;
		if (state != null) {
			if (customer_id != null) {
				sql = "select * from t_customer_return_list where customer_return_number like ? and customer_id = ? and state = ? and customer_return_date >= ? and  customer_return_date <=?";
				list = qr.query(sql, new BeanListHandler<CustomerReturnList>(CustomerReturnList.class),
						"%" + customer_return_number + "%", customer_id, state, bCustomerReturnDate,
						eCustomerReturnDate);
			} else {
				sql = "select * from t_customer_return_list where customer_return_number like ? and  state = ? and customer_return_date >= ? and  customer_return_date <=?";
				list = qr.query(sql, new BeanListHandler<CustomerReturnList>(CustomerReturnList.class),
						"%" + customer_return_number + "%", state, bCustomerReturnDate, eCustomerReturnDate);
			}
		} else {
			if (customer_id != null) {
				sql = "select * from t_customer_return_list where customer_return_number like ? and customer_id = ? and customer_return_date >= ? and  customer_return_date <=?";
				list = qr.query(sql, new BeanListHandler<CustomerReturnList>(CustomerReturnList.class),
						"%" + customer_return_number + "%", customer_id, bCustomerReturnDate, eCustomerReturnDate);
			} else {
				sql = "select * from t_customer_return_list where customer_return_number like ? and customer_return_date >= ? and  customer_return_date <=?";
				list = qr.query(sql, new BeanListHandler<CustomerReturnList>(CustomerReturnList.class),
						"%" + customer_return_number + "%", bCustomerReturnDate, eCustomerReturnDate);
			}
		}
		return list;
	}

	@Override
	public List<CustomerReturnListGoods> findCustomerReturnListGoodsAll(Integer customer_return_list_id)
			throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "";
		List<CustomerReturnListGoods> list = null;
		sql = "select * from t_customer_return_list_goods where customer_return_list_id=?";
		list = qr.query(sql, new BeanListHandler<CustomerReturnListGoods>(CustomerReturnListGoods.class),
				customer_return_list_id);
		return list;
	}

	@Override
	public List<CustomerReturnListCount> findListCount(String bCustomerReturnDate, String eCustomerReturnDate) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_customer_return_list where customer_return_date >= ? and customer_return_date <=?";
		List<CustomerReturnListCount> list = qr.query(sql, new BeanListHandler<CustomerReturnListCount>(CustomerReturnListCount.class),bCustomerReturnDate,eCustomerReturnDate);
		return list;
	}
}