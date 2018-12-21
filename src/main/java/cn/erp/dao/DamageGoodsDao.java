package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.DamageListGoods;
import cn.erp.domain.GoodsType;

public interface DamageGoodsDao {
	public List<DamageListGoods> findById(int damage_list_id)throws SQLException;
	public DamageListGoods findOne(int damage_list_id)throws SQLException;
}
