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
	/***
	 * @author wangshu
	 */
	public int insertGoodsType(GoodsType goodsType) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "insert into t_goodstype(name,p_id,state,icon) values(?,?,?,?)";
		return qr.update(sql, goodsType.getName(),goodsType.getP_id(),goodsType.getState(),goodsType.getIcon());
	}
	@Override
	public int delete(int id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "delete from t_goodstype where id = ?";
		return qr.update(sql,id);
	}

}
