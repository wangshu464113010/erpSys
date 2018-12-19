package cn.erp.dao;

import java.sql.SQLException;

import cn.erp.domain.GoodsType;

public interface GoodsTypeDao {
	public GoodsType findOne(int type_id)throws SQLException;
}
