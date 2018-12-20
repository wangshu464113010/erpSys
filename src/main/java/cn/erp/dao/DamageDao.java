package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Damaggoods;

//操作商品损坏的接口
public interface DamageDao{
	
	public void insertdamage(Damaggoods damaggoods) throws SQLException;
}
