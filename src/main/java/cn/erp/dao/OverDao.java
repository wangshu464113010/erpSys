package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Damaggoods;
import cn.erp.domain.Overgoods;
//操作商品溢出的接口
public interface OverDao {
	public void insertover(Overgoods overgoods) throws SQLException;
}
