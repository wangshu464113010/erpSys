package cn.erp.service;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.SaleList;

public interface SaleListService {
	public int addSaleList(SaleList saleList) throws SQLException;
	
	public List<SaleList> selectByCondition(String sale_number,Integer customer_id,Integer state,String bSaleDate,String eSaleDate) throws SQLException;
}
