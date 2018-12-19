package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.SaleList;
import cn.erp.domain.SaleListCondition;

public interface SaleListDao {
	
	public int insertSaleList(SaleList saleList) throws SQLException;
	public List<SaleList> selectByCondition(String sale_number,int customer_id,int state,String bSaleDate,String eSaleDate) throws SQLException;
}
