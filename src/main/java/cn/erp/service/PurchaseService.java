package cn.erp.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import cn.erp.domain.GoodsJson;
import cn.erp.domain.PurchaseList;
import cn.erp.domain.Purchase_List;
import cn.erp.domain.Purchase_List_Goods;
import cn.erp.utils.C3P0Util;

public interface PurchaseService {
	public List<PurchaseList> purchaseList()throws SQLException;
	
	public int saveJh(int user_id,String supplier_id,float amount_payable,float amount_paid,Date purchase_date,String remarks,int state,ArrayList<GoodsJson> goodsJson,String purchase_number) throws SQLException;

	public List<Purchase_List> findAllPurchaseSearch(String purchaseNumber,Integer supplier_id,Integer state,String bPurchaseDate,String ePurchaseDate)throws SQLException;

	public List<Purchase_List_Goods> findAllListGoodsById(Integer id) throws SQLException;
	
	public int deletePurchaseList(Integer id)throws SQLException;
	
	public int updataState(int id) throws SQLException;
	
	public String getPurchaseNumber(String date) throws SQLException;
}	
