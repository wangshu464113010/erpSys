package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.erp.dao.SaleListDao;
import cn.erp.domain.CustomerReturnList;
import cn.erp.domain.CustomerReturnListCount;
import cn.erp.domain.Purchase_List;
import cn.erp.domain.Purchase_List_Goods;
import cn.erp.domain.SaleList;
import cn.erp.domain.SaleListCount;
import cn.erp.domain.SaleListGoods;
import cn.erp.utils.C3P0Util;

public class SaleListDaoImpl implements SaleListDao {

	@Override
	public int insertSaleList(SaleList saleList) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "insert into t_sale_list values(default,?,?,?,?,?,?,?,?)";
		int i = qr.update(sql, saleList.getAmount_paid(), saleList.getAmount_payable(), saleList.getRemarks(),
				saleList.getSale_date(), saleList.getSale_number(), saleList.getState(), saleList.getUser_id(),
				saleList.getCustomer_id());
		return i;
	}

	@Override
	public List<SaleList> selectByCondition(String sale_number, Integer customer_id, Integer state, String bSaleDate,
			String eSaleDate) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "";
		List<SaleList> list = null;

		if (state != null) {
			if (customer_id != null) {
				sql = "select * from t_sale_list where sale_number like ? and customer_id=? and state=? and sale_date>=? and sale_date<=?";
				list = qr.query(sql, new BeanListHandler<SaleList>(SaleList.class), "%" + sale_number + "%",
						customer_id, state, bSaleDate, eSaleDate);
			} else {
				sql = "select * from t_sale_list where sale_number like ? and state=? and sale_date>=? and sale_date<=?";
				list = qr.query(sql, new BeanListHandler<SaleList>(SaleList.class), "%" + sale_number + "%", state,
						bSaleDate, eSaleDate);
			}
		} else {
			if (customer_id != null) {
				sql = "select * from t_sale_list where sale_number like ? and customer_id=? and sale_date>=? and sale_date<=?";
				list = qr.query(sql, new BeanListHandler<SaleList>(SaleList.class), "%" + sale_number + "%",
						customer_id, bSaleDate, eSaleDate);
			} else {
				sql = "select * from t_sale_list where sale_number like ? and sale_date>=? and sale_date<=?";
				list = qr.query(sql, new BeanListHandler<SaleList>(SaleList.class), "%" + sale_number + "%", bSaleDate,
						eSaleDate);
			}
		}
		return list;
	}

	@Override
	public SaleList findById(Integer id) throws SQLException {
		String sql = "select * from t_sale_list where id = ?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql, new BeanHandler<SaleList>(SaleList.class), id);
	}

	@Override
	public List<SaleListGoods> findAllBySaleListId(Integer id) throws SQLException {
		String sql = "select * from t_sale_list_goods where sale_list_id = ?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql, new BeanListHandler<SaleListGoods>(SaleListGoods.class), id);
	}

	@Override
	public int deleteById(int id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "delete from t_sale_list where id=?";
		int i = qr.update(sql, id);
		return i;
	}

	@Override
	public List<SaleList> findByMaxId() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_sale_list";
		List<SaleList> list = qr.query(sql, new BeanListHandler<SaleList>(SaleList.class));
		return list;
	}

	@Override
	public int updataState(int id) throws SQLException {
		String sql = "update t_sale_list set state=1 where id=?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.update(sql, id);
	}

	@Override
	public List<SaleListCount> findListCount(String bSaleDate, String eSaleDate) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_sale_list where sale_date >= ? and sale_date <=?";
		List<SaleListCount> list = qr.query(sql, new BeanListHandler<SaleListCount>(SaleListCount.class),bSaleDate,eSaleDate);
		return list;
	}

	@Override
	public String findSaleNumber() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select max(sale_number) from t_sale_list";
		String saleNumber = (String) qr.query(sql, new ScalarHandler());
		return saleNumber;
	}

}
