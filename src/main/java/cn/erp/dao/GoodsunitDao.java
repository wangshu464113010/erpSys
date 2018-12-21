package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Goodsunit;
/**
 * GoodsunitDao层接口
 * @author wangshu
 *
 */
public interface GoodsunitDao {
	
	public List<Goodsunit> getAll() throws SQLException;
	public int insert(Goodsunit goodsunit) throws SQLException;
	//public int update(Goodsunit goodsunit) throws SQLException;
	public int delete(Goodsunit goodsunit) throws SQLException;
	
	
	
	
}
