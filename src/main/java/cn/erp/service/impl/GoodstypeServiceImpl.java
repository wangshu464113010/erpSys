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
	public int delete(int id) throws SQLException {
		return goodstypeDao.delete(id);
	}
	@Override
	public int addGoodsType(GoodsType goodstype) throws SQLException {
		goodstype.setIcon("icon-folder");//鍏蜂綋杩樹笉娓呮璇ュ�兼剰鎬�?
		goodstype.setState(0);//鐘舵�乻tate鍊奸粯璁や负0(鍙兘鏄笌鍚敤涓庡仠鐢ㄦ湁鍏�?)
		return goodstypeDao.insertGoodsType(goodstype);
	}

}
