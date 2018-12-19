package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.SaleListDao;
import cn.erp.domain.SaleList;
import cn.erp.domain.SaleListCondition;
import cn.erp.utils.C3P0Util;

public class SaleListDaoImpl implements SaleListDao{

	@Override
	public int insertSaleList(SaleList saleList) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "insert into t_sale_list values(default,?,?,?,?,?,?,?,?)";
		int i = qr.update(sql,saleList.getAmount_paid(),saleList.getAmount_payable(),saleList.getRemarks(),saleList.getSale_date(),saleList.getSale_number(),saleList.getState(),saleList.getUser_id(),saleList.getCustomer_id());
		return i;
	}

	@Override
	public List<SaleList> selectByCondition(String sale_number,int customer_id,int state,String bSaleDate,String eSaleDate) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "";
		List<SaleList> list = null;
		if("".equals(sale_number)&&customer_id==0){
			sql = "select * from t_sale_list where state=? and sale_date>=? and sale_date<=?";
			list = qr.query(sql, new BeanListHandler<SaleList>(SaleList.class),state,bSaleDate,eSaleDate);
		}else if(!"".equals(sale_number)&&customer_id==0){
			sql = "select * from t_sale_list where sale_number=? and state=? and sale_date>=? and sale_date<=?";
			list = qr.query(sql, new BeanListHandler<SaleList>(SaleList.class),sale_number,state,bSaleDate,eSaleDate);
		}else if("".equals(sale_number)&&customer_id!=0){
			sql = "select * from t_sale_list where customer_id=? and state=? and sale_date>=? and sale_date<=?";
			list = qr.query(sql, new BeanListHandler<SaleList>(SaleList.class),customer_id,state,bSaleDate,eSaleDate);
		}else{
			sql = "select * from t_sale_list where sale_number=? and customer_id=? and state=? and sale_date>=? and sale_date<=?";
			list = qr.query(sql, new BeanListHandler<SaleList>(SaleList.class),sale_number,customer_id,state,bSaleDate,eSaleDate);
		}
		return list;
	}

}
