package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Return;
import cn.erp.domain.Return_List;
import cn.erp.domain.Return_List_Goods;

public interface ReturnDao {
	public List<Return> findAllByP_id(int p_id) throws SQLException;
	
	public int saveReturnListGoods(Return_List_Goods returnListGoods) throws SQLException;
	
	public int saveReturnList(Return_List returnList) throws SQLException;
	
	public Return_List findId(String return_number,Integer supplier_id,Integer user_id)throws SQLException;
	
	public List<Return_List> findAllByTj(String returnNumber, Integer supplier_id, Integer state,String bReturnDate, String eReturnDate)throws SQLException;

	public List<Return_List_Goods> findAllByReturnListId(Integer id) throws SQLException;

	public Return_List findById(Integer id)throws SQLException;
	
	public int deleteReturnListById(Integer id)throws SQLException;
	
	public int deleteReturnListGoodsByReturnListId(Integer id)throws SQLException;



	
}
