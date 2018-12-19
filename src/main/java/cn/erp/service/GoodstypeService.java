package cn.erp.service;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.GoodsType;

public interface GoodstypeService {
	GoodsType findByPid(int pid) throws SQLException;
	List<GoodsType> findAllByPid(int id) throws SQLException;
}
