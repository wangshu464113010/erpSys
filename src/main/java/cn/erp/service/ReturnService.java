package cn.erp.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import cn.erp.domain.GoodsJson;
import cn.erp.domain.Purchase_List_Goods;
import cn.erp.domain.ReturnList;
import cn.erp.domain.Return_List;
import cn.erp.domain.Return_List_Goods;

public interface ReturnService {
	public List<ReturnList> ReturnList()throws SQLException;
	public int returnJh(int user_id,String supplier_id,float amount_payable,float amount_paid,Date return_date,String remarks,int state,GoodsJson goodsJson,String return_number) throws SQLException;
	public List<Return_List_Goods> findAllListGoodsById(Integer id) throws SQLException;
	public int deleteReturnList(Integer id)throws SQLException;
	List<Return_List> findAllReturnSearch(String returnNumber, Integer supplier_id, Integer state, String bReturnDate,
			String eReturnDate) throws SQLException;



}
