package cn.erp.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.erp.dao.GoodsTypeDao;
import cn.erp.domain.GoodsType;
import cn.erp.utils.C3P0Util;

public class GoodsTypeDaoImpl implements GoodsTypeDao {

	@Override
	public GoodsType findOne(int type_id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_goodstype where id = ?";
		return qr.query(sql, new BeanHandler<GoodsType>(GoodsType.class),type_id);
	}

}
