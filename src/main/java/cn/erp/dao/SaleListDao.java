package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.CustomerReturnList;
import cn.erp.domain.CustomerReturnListCount;
import cn.erp.domain.SaleList;
import cn.erp.domain.SaleListCondition;
import cn.erp.domain.SaleListCount;
import cn.erp.domain.SaleListGoods;

public interface SaleListDao {
	
	public int insertSaleList(SaleList saleList) throws SQLException;
	public List<SaleList> selectByCondition(String sale_number,Integer customer_id,Integer state,String bSaleDate,String eSaleDate) throws SQLException;
	public SaleList findById(Integer id) throws SQLException;
	public List<SaleListGoods> findAllBySaleListId(Integer id) throws SQLException;
	public int deleteById(int id) throws SQLException;
	public List<SaleList> findByMaxId() throws SQLException;
	public List<SaleList> findByDate(String begin,String end) throws SQLException;
	public int updataState(int id)throws SQLException;
	public String findSaleNumber() throws SQLException;;
}
