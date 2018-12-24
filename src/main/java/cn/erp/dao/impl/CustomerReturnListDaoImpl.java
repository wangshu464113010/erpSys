package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.erp.dao.CustomerReturnListDao;
import cn.erp.domain.CustomerReturnList;
import cn.erp.domain.CustomerReturnListCount;
import cn.erp.domain.CustomerReturnListGoods;
import cn.erp.domain.User;
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
	public int deleteById(int customer_return_list_id) throws SQLException {
		String sql = "";
		String sql1 = "";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		QueryRunner qr1 = new QueryRunner(C3P0Util.getDataSource());
		//先删除从表才能删除主表
		sql1 = "delete from t_customer_return_list_goods where customer_return_list_id=?";
		int i = qr1.update(sql1,customer_return_list_id);
		if(i==0){
			sql = "delete  from t_customer_return_list where id=?";
			qr.update(sql,customer_return_list_id);
			i = 1;
		}else{
			sql = "delete  from t_customer_return_list where id=?";
			qr.update(sql,customer_return_list_id);
		}
		return i;
	}
	//增加单号
	@Override
	public int insertCustomerRetrunList(Double amount_paid, Double amount_payable, String customer_return_date,
			String customer_return_number,String remarks, Integer state, Integer customer_id) throws SQLException {
		String sql = "";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		sql = "select max(customer_return_number) from t_customer_return_list";
        String s =  (String) qr.query(sql,new ScalarHandler());
		if(s.equals(customer_return_number)){
		}else{
			String c1 = customer_return_number.substring(0,customer_return_number.length()-5);
			String c2 = customer_return_number.substring(9,customer_return_number.length());
			int a = Integer.parseInt(c2)+1;
			customer_return_number = c1+a;
		}
		sql = "insert into t_customer_return_list values(default,?,?,?,?,?,?,?,?)";
		User user = new User();
		user.setId(1);
		Integer user_id = user.getId();
		
		int i = qr.update(sql,amount_paid,amount_payable,customer_return_date,customer_return_number,remarks,
							state,user_id,customer_id );
		return i;
	}
	public List<CustomerReturnListCount> findListCount(String bCustomerReturnDate, String eCustomerReturnDate) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_customer_return_list where customer_return_date >= ? and customer_return_date <=?";
		List<CustomerReturnListCount> list = qr.query(sql, new BeanListHandler<CustomerReturnListCount>(CustomerReturnListCount.class),bCustomerReturnDate,eCustomerReturnDate);
		return list;
	}
}