package cn.erp.service;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Goods;

public interface GoodsService {
	public List<Goods> findAll(Integer page,Integer rows,Integer type_id1,String codeOrName) throws SQLException;
	public int count() throws SQLException;
	public List<Goods> findAll() throws SQLException;
	
	//wangshu
	public String getMaxGoodsCode()throws SQLException;
	public  List<Goods> findLikeGoods(String name,int pageNow,int pageSize)throws SQLException;
	public int countLikeGoods(String name)throws SQLException;
	public int insert(Goods goods)throws SQLException;
	public int update(Goods goods)throws SQLException;
	public int delete(int id)throws SQLException;
}	
