package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.ReturnListGoodsVODao;
import cn.erp.domain.ReturnListGoodsVO;
import cn.erp.utils.C3P0Util;

public class ReturnListGoodsVODaoImpl implements ReturnListGoodsVODao {

	@Override
	public List<ReturnListGoodsVO> findByPurchaseListId(int purchaseListId) throws SQLException {
		String sql = "select "
				+ "id,code,model,name,num,total,unit,price,"
				+ " return_list_id  returnListId,"
				+"type_id typeId, goods_id goodsId "
				+ " from t_return_list_goods where return_list_id = ?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql,
				new BeanListHandler<ReturnListGoodsVO>(ReturnListGoodsVO.class),
				purchaseListId);
	}

	@Override
	public List<ReturnListGoodsVO> findByPurchaseListIdAndCodeOrName(int purchaseListId, String codeOrName)
			throws SQLException {
		String sql = "select "
				+ "id,code,model,name,num,total,unit,price,"
				+ " return_list_id  returnListId,"
				+"type_id typeId, goods_id goodsId "
				+ " from t_return_list_goods where return_list_id = ?"
				+ " and ( code like ? or name like ?)";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql,
				new BeanListHandler<ReturnListGoodsVO>(ReturnListGoodsVO.class),
				purchaseListId,"%"+codeOrName+"%","%"+codeOrName+"%");
	}

}
