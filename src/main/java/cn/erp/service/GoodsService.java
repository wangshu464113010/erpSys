package cn.erp.service;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Goods;

public interface GoodsService {
	public List<Goods> findAll() throws SQLException;
	public int count() throws SQLException;
}	
