package cn.erp.service;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Goods;

public interface GoodsService {
	List<Goods> findAll() throws SQLException;
}
