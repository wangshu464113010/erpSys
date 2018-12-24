package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.ReturnListGoodsVO;

public interface ReturnListGoodsVODao {
	public List<ReturnListGoodsVO> findByPurchaseListId(int purchaseListId) throws SQLException;
	public List<ReturnListGoodsVO> findByPurchaseListIdAndCodeOrName(int purchaseListId,String codeOrName) throws SQLException;
	
}
