package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.DamageDao;
import cn.erp.domain.Damaggoods;
import cn.erp.domain.Goods;
import cn.erp.utils.C3P0Util;

public class DamageDaoImpl implements DamageDao{

	@Override
	public void insertdamage(Damaggoods damaggoods) throws SQLException {
		 QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		 String sql = "insert into t_damage_list_goods(code,model,name,num,price,total,unit,type_Id,goods_Id) values(?,?,?,?,?,?,?,?,?)";
			int i  =  qr.update(sql,damaggoods.getCode(),damaggoods.getModel(),damaggoods.getName(),damaggoods.getNum(),
					damaggoods.getPrice(),damaggoods.getTotal(),damaggoods.getUnit(),damaggoods.getType_id(),damaggoods.getGoods_id());
	}

}
