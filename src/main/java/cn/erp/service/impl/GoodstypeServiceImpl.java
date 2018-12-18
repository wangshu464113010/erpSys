package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.List;

import cn.erp.dao.GoodstypeDao;
import cn.erp.dao.impl.GoodstypeDaoImpl;
import cn.erp.domain.Goodstype;
import cn.erp.service.GoodstypeService;

public class GoodstypeServiceImpl implements GoodstypeService {
	
	private GoodstypeDao goodstypeDao = new GoodstypeDaoImpl();

	@Override
	public Goodstype findByPid(int pid) throws SQLException {
		return goodstypeDao.findByPid(pid);
	}

	@Override
	public List<Goodstype> findAllByPid(int id) throws SQLException {
		List<Goodstype> parent = goodstypeDao.findAllByPid(-1);
		parent.get(0);
		return null;
	}

	

}
