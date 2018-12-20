package cn.erp.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import cn.erp.dao.OverDao;
import cn.erp.domain.Overgoods;
import cn.erp.utils.C3P0Util;

public class OverDaoImpl implements OverDao{

	@Override
	public void insertover(Overgoods overgoods) throws SQLException {
		 QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		 String sql = "insert into t_overflow_list_goods(code,model,name,num,price,total,unit,type_Id,goods_Id) values(?,?,?,?,?,?,?,?,?)";
			int i  =  qr.update(sql,overgoods.getCode(),overgoods.getModel(),overgoods.getName(),overgoods.getNum(),
					overgoods.getPrice(),overgoods.getTotal(),overgoods.getUnit(),overgoods.getType_id(),overgoods.getGoods_id());
	}
	
}
