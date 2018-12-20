package cn.erp.dao;

import java.sql.SQLException;

import cn.erp.domain.Overgoods;
//鎿嶄綔鍟嗗搧婧㈠嚭鐨勬帴鍙�
public interface OverDao {
	public void insertover(Overgoods overgoods) throws SQLException;
}
