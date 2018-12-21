package cn.erp.service;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.CountSale;
import cn.erp.domain.CustomerReturnListCount;
import cn.erp.domain.SaleList;
import cn.erp.domain.SaleListCount;
import cn.erp.domain.SaleListGoods;

public interface SaleListService {
	public int addSaleList(SaleList saleList) throws SQLException;
	
	public List<SaleList> selectByCondition(String sale_number,Integer customer_id,Integer state,String bSaleDate,String eSaleDate) throws SQLException;

	public List<SaleListGoods> findAllListGoodsById(Integer id) throws SQLException;
	
	public int deleteById(int id) throws SQLException;
	
	public int findByMaxId() throws SQLException;
	
	public List<CountSale> findByMonthTj(String begin, String end) throws SQLException;
	
	public int updataState(int id) throws SQLException;
	
	public List<SaleListCount> findListCount(String bSaleDate, String eSaleDate,Integer type_id, String codeOrName) throws SQLException;
	
	public String findSaleNumber() throws SQLException;
}
