package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.PurchaseDao;
import cn.erp.domain.Purchase;
import cn.erp.utils.C3P0Util;

public class PurchaseDaoImpl implements PurchaseDao{

	@Override
	public List<Purchase> findAllByP_id(int p_id) throws SQLException {
		String sql = "select * from t_goodstype where p_id=?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql,new BeanListHandler<Purchase>(Purchase.class),p_id);
	}

}
