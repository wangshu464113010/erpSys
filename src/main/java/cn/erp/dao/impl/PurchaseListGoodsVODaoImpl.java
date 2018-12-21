package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.PurchaseListGoodsVODao;
import cn.erp.domain.PurchaseListGoodsVO;
import cn.erp.utils.C3P0Util;

public class PurchaseListGoodsVODaoImpl implements PurchaseListGoodsVODao {

	@Override
	public List<PurchaseListGoodsVO> findByPurchaseListId(int purchaseListId) throws SQLException {
		String sql = "select "
				+ "id,code,model,name,num,total,unit,price,"
				+ " purchase_list_id  purchaseListId,"
				+"type_id typeId, goods_id goodsId "
				+ " from t_purchase_list_goods where purchase_list_id = ?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql,
				new BeanListHandler<PurchaseListGoodsVO>(PurchaseListGoodsVO.class),
				purchaseListId);
	}

}
