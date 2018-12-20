package cn.erp.service;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.GoodsType;

public interface GoodstypeService {
	GoodsType findByPid(int pid) throws SQLException;
	List<GoodsType> findAllByPid(int id) throws SQLException;
	/**
	 * @author wangshu
	 * @param goodstype 浼犲叆鍟嗗搧绫诲瀷
	 * @return 杩斿洖int绫诲瀷鍊�
	 * @throws SQLException
	 */
	public int addGoodsType(GoodsType goodstype)throws SQLException;
	public int delete(int id) throws SQLException;
}
