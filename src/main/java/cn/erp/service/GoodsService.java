package cn.erp.service;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Goods;

public interface GoodsService {
	public List<Goods> findAll(Integer page,Integer rows,Integer type_id1) throws SQLException;
	public int count() throws SQLException;
}	
