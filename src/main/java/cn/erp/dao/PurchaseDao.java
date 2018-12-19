package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import javax.swing.plaf.OptionPaneUI;

import cn.erp.domain.Purchase;
import cn.erp.domain.Purchase_List;
import cn.erp.domain.Purchase_List_Goods;


public interface PurchaseDao {
	public List<Purchase> findAllByP_id(int p_id) throws SQLException;
	
	public int savePurchaseListGoods(Purchase_List_Goods purchaseListGoods) throws SQLException;
	
	public int savePurchaseList(Purchase_List purchaseList) throws SQLException;
	
	public Purchase_List findId(String purchase_number,Integer supplier_id,Integer user_id)throws SQLException;

	public List<Purchase_List> findAllByTj(String purchaseNumber, Integer supplier_id, Integer state,String bPurchaseDate, String ePurchaseDate)throws SQLException;

	public List<Purchase_List_Goods> findAllByPurchaseListId(Integer id) throws SQLException;

	public Purchase_List findById(Integer id)throws SQLException;
	
	public int deletePuchaseListById(Integer id)throws SQLException;
	
	public int deletePuchaseListGoodsByPuchaseListId(Integer id)throws SQLException;
}
