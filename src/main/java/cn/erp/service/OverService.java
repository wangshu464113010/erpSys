package cn.erp.service;

import java.sql.SQLException;

import cn.erp.domain.Overgoods;

public interface OverService {	
	public void insertover(Overgoods overgoods) throws SQLException;
}
