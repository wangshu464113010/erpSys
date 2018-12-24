package cn.erp.service.impl;

import java.sql.SQLException;

import cn.erp.dao.OverlistDao;
import cn.erp.dao.impl.OverlistDaoImpl;
import cn.erp.domain.Overlist;
import cn.erp.service.OverlistService;


public class OverlistServiceImpl implements OverlistService{

	OverlistDao overlistdao=new OverlistDaoImpl();
	@Override
	public void insertOverlist(Overlist overlist) throws SQLException {
		// TODO Auto-generated method stub
		overlistdao.insertoverlist(overlist);
	}
	@Override
	public Integer getMaxId(Overlist overlist) throws SQLException {
		return overlistdao.getMaxId(overlist);
	}

}
