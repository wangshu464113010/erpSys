package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Goods;

public interface ListAlarmDao {
	public List<Goods> findAll() throws SQLException;
}
