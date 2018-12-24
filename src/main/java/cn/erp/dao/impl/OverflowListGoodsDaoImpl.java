package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.OverflowListGoodsDao;
import cn.erp.domain.OverflowListGoods;
import cn.erp.utils.C3P0Util;

public class OverflowListGoodsDaoImpl implements OverflowListGoodsDao{

	@Override
	public List<OverflowListGoods> findAll(int overflowListId) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_overflow_list_goods where overflow_list_id=? ";
		List<OverflowListGoods> list = qr.query(sql, new BeanListHandler<OverflowListGoods>(OverflowListGoods.class),overflowListId);
		return list;
	}

}
