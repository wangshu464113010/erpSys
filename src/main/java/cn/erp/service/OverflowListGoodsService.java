package cn.erp.service;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.OverflowListGoods;

public interface OverflowListGoodsService {
	public List<OverflowListGoods> findAll(int overflowListId) throws SQLException;
}
