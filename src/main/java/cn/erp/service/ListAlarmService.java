package cn.erp.service;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Goods;

public interface ListAlarmService {
	public List<Goods> findAll() throws SQLException;
}
