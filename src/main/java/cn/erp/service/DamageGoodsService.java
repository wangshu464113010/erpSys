package cn.erp.service;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.DamageListGoods;
import cn.erp.domain.GoodsType;

public interface DamageGoodsService {
	public List<DamageListGoods> findBydamage_list_id(int damage_list_id) throws SQLException;
}
