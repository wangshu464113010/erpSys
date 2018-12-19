package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.List;

import cn.erp.dao.GoodsTypeDao;
import cn.erp.dao.impl.GoodsTypeDaoImpl;
import cn.erp.domain.GoodsType;
import cn.erp.service.GoodstypeService;

public class GoodstypeServiceImpl implements GoodstypeService {
	
	private GoodsTypeDao goodstypeDao = new GoodsTypeDaoImpl();

	@Override
	public GoodsType findByPid(int pid) throws SQLException {
		return goodstypeDao.findOne(pid);
	}

	@Override
	public List<GoodsType> findAllByPid(int id) throws SQLException {
		return null;
	}

	@Override
	public int addGoodsType(GoodsType goodstype) throws SQLException {
		goodstype.setIcon("icon-folder");//具体还不清楚该值意思?
		goodstype.setState(0);//状态state值默认为0(可能是与启用与停用有关?)
		return goodstypeDao.insertGoodsType(goodstype);
	}

}
