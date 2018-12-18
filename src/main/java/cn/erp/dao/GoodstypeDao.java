package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Goodstype;

public interface GoodstypeDao {
	Goodstype findByPid(int pid) throws SQLException;
	List<Goodstype> findAllByPid(int pid) throws SQLException;
}
