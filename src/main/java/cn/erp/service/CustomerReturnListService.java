package cn.erp.service;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Customer;
import cn.erp.domain.CustomerReturnList;
import cn.erp.domain.CustomerReturnListCount;
import cn.erp.domain.CustomerReturnListGoods;

public interface CustomerReturnListService {
	public int insertCustomerRetrunList(Double amount_paid,Double amount_payable,
			String customer_return_date,String customer_return_number,
			String remarks,Integer state,Integer customer_id) throws SQLException;
	
	public List<CustomerReturnList> findCustomerReturnListAll(String customer_return_number, Integer customer_id,
			Integer state, String bCustomerReturnDate,String eCustomerReturnDate) throws SQLException;
	
	public List<CustomerReturnListGoods> findCustomerReturnListGoodsAll(Integer customer_return_list_id) throws SQLException;
	
	public int deleteById(int customer_return_list_id) throws SQLException;
	
	public List<CustomerReturnListCount> findListCount(String bCustomerReturnDate, String eCustomerReturnDate,Integer type_id, String codeOrName) throws SQLException;
}
