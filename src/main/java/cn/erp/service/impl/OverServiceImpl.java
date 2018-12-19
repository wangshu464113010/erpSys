package cn.erp.service.impl;

import java.sql.SQLException;

import cn.erp.dao.OverDao;
import cn.erp.dao.impl.OverDaoImpl;
import cn.erp.domain.Overgoods;
import cn.erp.service.OverService;
import cn.erp.utils.C3P0Util;


public class OverServiceImpl implements OverService{
	
	private OverDao overdao=new OverDaoImpl();

	@Override
	public void insertover(Overgoods overgoods) throws SQLException {
		overdao.insertover(overgoods);
	}

}
