package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.PurchaseListGoodsVO;

public interface PurchaseListGoodsVODao {
	public List<PurchaseListGoodsVO> findByPurchaseListId(int purchaseListId) throws SQLException;
	public List<PurchaseListGoodsVO> findByPurchaseListIdAndCodeOrName(int purchaseListId,String codeOrName) throws SQLException;
	
}
