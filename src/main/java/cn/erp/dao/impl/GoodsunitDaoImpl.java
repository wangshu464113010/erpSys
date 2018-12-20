package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.GoodsunitDao;
import cn.erp.domain.Goodsunit;
import cn.erp.utils.C3P0Util;

public class GoodsunitDaoImpl implements GoodsunitDao {

	@Override
	public List<Goodsunit> getAll() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_goodsunit";
		return qr.query(sql, new BeanListHandler<Goodsunit>(Goodsunit.class));
	}

	@Override
	public int insert(Goodsunit goodsunit) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "insert into t_goodsunit(name) values(?)";
		return  qr.update(sql,goodsunit.getName());
	}

	@Override
	public int delete(Goodsunit goodsunit) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "delete from t_goodsunit where id = ?";
		return  qr.update(sql,goodsunit.getId());
	}

}
