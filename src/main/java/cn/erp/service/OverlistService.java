package cn.erp.service;

import java.sql.SQLException;

import cn.erp.domain.Overlist;

public interface OverlistService {
	public void insertOverlist(Overlist overlist)throws SQLException;
}
