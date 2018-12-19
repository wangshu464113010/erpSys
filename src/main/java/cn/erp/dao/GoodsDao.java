package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Goods;

public interface GoodsDao {
	public List<Goods> findAll(Integer page,Integer rows,Integer type_id) throws SQLException;
	public int count() throws SQLException;
	public List<Goods> findAll() throws SQLException;
	//获取最大的商品编号
	public String getMaxGoodsCode()throws SQLException;
	public int insert(Goods goods)throws SQLException;
	public int update(Goods goods)throws SQLException;
	public int delete(int id)throws SQLException;
	
}
