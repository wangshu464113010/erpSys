package cn.erp.dao;

import java.sql.SQLException;

import cn.erp.domain.GoodsType;
/**
 * 
 * @author wangshu
 *
 */
public interface GoodsTypeDao {
	
	public GoodsType findOne(int type_id)throws SQLException;

	public int insertGoodsType(GoodsType goodsType) throws SQLException;
	public int delete(int id) throws SQLException;
	
}
