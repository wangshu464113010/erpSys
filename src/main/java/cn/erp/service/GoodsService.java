package cn.erp.service;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Goods;

public interface GoodsService {
	public List<Goods> findAll(Integer page,Integer rows,Integer type_id1) throws SQLException;
	public int count() throws SQLException;
	public List<Goods> findAll() throws SQLException;
	
	//获取商品code中最大的值
	public String getMaxGoodsCode()throws SQLException;
}	
