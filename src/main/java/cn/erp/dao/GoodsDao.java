package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Goods;
import cn.erp.domain.IntegerDO;
import cn.erp.domain.Page;

public interface GoodsDao {
	public List<Goods> findAll(Integer page,Integer rows,Integer type_id,String codeOrName) throws SQLException;
	public int count() throws SQLException;
	public List<Goods> findAll() throws SQLException;
	
	//wangshu
	public List<Goods> findLikeGoods(String name,Page page) throws SQLException;
	public IntegerDO countLikeGoods(String name) throws SQLException;
	
	public String getMaxGoodsCode()throws SQLException;
	public int insert(Goods goods)throws SQLException;
	public int update(Goods goods)throws SQLException;
	public int delete(int id)throws SQLException;
	
	public Goods findGoodsByCode(String code) throws SQLException;
	
}
