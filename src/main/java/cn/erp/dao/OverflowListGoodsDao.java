package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.OverflowListGoods;

public interface OverflowListGoodsDao {
	public List<OverflowListGoods> findAll(int overflowListId) throws SQLException;
	
}
