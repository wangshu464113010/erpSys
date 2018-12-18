package cn.erp.service;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Goodstype;

public interface GoodstypeService {
	Goodstype findByPid(int pid) throws SQLException;
	List<Goodstype> findAllByPid(int id) throws SQLException;
}
