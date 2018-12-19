package cn.erp.service;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.GoodsType;

public interface GoodstypeService {
	GoodsType findByPid(int pid) throws SQLException;
	List<GoodsType> findAllByPid(int id) throws SQLException;
	/**
	 * @author wangshu
	 * @param goodstype 传入商品类型
	 * @return 返回int类型值
	 * @throws SQLException
	 */
	public int addGoodsType(GoodsType goodstype)throws SQLException;
}
