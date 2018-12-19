package cn.erp.service;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Goodsunit;

public interface GoodsunitService {
	
	public List<Goodsunit> queryAll()throws SQLException;
	public int add(Goodsunit goodsunit) throws SQLException;
	public int delete(Goodsunit goodsunit) throws SQLException;
	
	
}
