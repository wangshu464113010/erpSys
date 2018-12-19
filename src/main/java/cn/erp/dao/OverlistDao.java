package cn.erp.dao;

import java.sql.SQLException;

import cn.erp.domain.Overlist;

public interface OverlistDao {
	//向t_overflow_list里面插入数据
	public void insertoverlist(Overlist overlist)throws SQLException;
}
