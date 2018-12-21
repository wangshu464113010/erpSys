package cn.erp.dao.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.PurchaseDao;
import cn.erp.domain.Purchase;
import cn.erp.domain.Purchase_List;
import cn.erp.domain.Purchase_List_Goods;
import cn.erp.utils.C3P0Util;

public class PurchaseDaoImpl implements PurchaseDao{

	@Override
	public List<Purchase> findAllByP_id(int p_id) throws SQLException {
		String sql = "select * from t_goodstype where p_id=?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql,new BeanListHandler<Purchase>(Purchase.class),p_id);
	}

	@Override
	public int savePurchaseListGoods(Purchase_List_Goods plg) throws SQLException {
		String sql = "insert into t_purchase_list_goods values(default,?,?,?,?,?,?,?,?,?,?)";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.update(sql,plg.getCode(),plg.getModel(),plg.getName(),plg.getNum(),plg.getPrice(),plg.getTotal(),plg.getUtil(),plg.getPurchase_list_id(),plg.getType_id(),plg.getGoods_id());
	}

	@Override
	public int savePurchaseList(Purchase_List pl) throws SQLException {
		String sql = "insert into t_purchase_list values(default,?,?,?,?,?,?,?,?)";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.update(sql,pl.getAmount_paid(),pl.getAmount_payable(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(pl.getPurchase_date()),pl.getRemarks(),pl.getState(),pl.getPurchase_number(),pl.getSupplier_id(),pl.getUser_id());
	}

	@Override
	public Purchase_List findId(String purchase_number, Integer supplier_id, Integer user_id) throws SQLException {
		String sql = "select * from t_purchase_list where purchase_number=? and supplier_id=? and user_id=?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql,new BeanHandler<Purchase_List>(Purchase_List.class),purchase_number,supplier_id,user_id);
	}

	@Override
	public List<Purchase_List> findAllByTj(String purchaseNumber, Integer supplier_id, Integer state,
			String bPurchaseDate, String ePurchaseDate) throws SQLException {
		if(state!=null){
			if(supplier_id!=null){
				String sql = "select * from t_purchase_list where purchase_number like ? and supplier_id = ? and state = ? and purchase_date >= ? and  purchase_date <=?";
				QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
				return qr.query(sql,new BeanListHandler<Purchase_List>(Purchase_List.class),"%"+purchaseNumber+"%",supplier_id,state,bPurchaseDate,ePurchaseDate);
			}else{
				String sql = "select * from t_purchase_list where purchase_number like ? and state = ? and purchase_date >= ? and  purchase_date <=?";
				QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
				return qr.query(sql,new BeanListHandler<Purchase_List>(Purchase_List.class),"%"+purchaseNumber+"%",state,bPurchaseDate,ePurchaseDate);
			}
			
		}else{
			if(supplier_id!=null){
				String sql = "select * from t_purchase_list where purchase_number like ? and supplier_id = ? and purchase_date >= ? and  purchase_date <=?";
				QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
				return qr.query(sql,new BeanListHandler<Purchase_List>(Purchase_List.class),"%"+purchaseNumber+"%",supplier_id,bPurchaseDate,ePurchaseDate);
			}else{
				String sql = "select * from t_purchase_list where purchase_number like ? and purchase_date >= ? and  purchase_date <=?";
				QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
				return qr.query(sql,new BeanListHandler<Purchase_List>(Purchase_List.class),"%"+purchaseNumber+"%",bPurchaseDate,ePurchaseDate);
			}
				
		}
	}

	@Override
	public List<Purchase_List_Goods> findAllByPurchaseListId(Integer id) throws SQLException {
		String sql = "select * from t_purchase_list_goods where purchase_list_id = ?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql,new BeanListHandler<Purchase_List_Goods>(Purchase_List_Goods.class),id);
	}

	@Override
	public Purchase_List findById(Integer id) throws SQLException {
		String sql = "select * from t_purchase_list where id = ?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql,new BeanHandler<Purchase_List>(Purchase_List.class),id);
	}

	@Override
	public int deletePuchaseListById(Integer id) throws SQLException {
		String sql = "delete from t_purchase_list where id=?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.update(sql,id);
	}

	@Override
	public int deletePuchaseListGoodsByPuchaseListId(Integer id) throws SQLException {
		String sql = "delete from t_purchase_list_goods where purchase_list_id=?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.update(sql,id);
	}

	@Override
	public int getPurchaseNumber(String date) throws SQLException {
		String sql = "select * from t_purchase_list where purchase_date=?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		List<Purchase_List> list = qr.query(sql,new BeanListHandler<Purchase_List>(Purchase_List.class),date);
		return list.size();
	}

	
	

}
