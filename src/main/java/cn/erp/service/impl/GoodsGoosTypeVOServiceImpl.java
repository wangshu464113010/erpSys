package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.List;

import cn.erp.dao.GoodsGoosTypeVODao;
import cn.erp.dao.GoodsTypeDao;
import cn.erp.dao.impl.GoodsGoosTypeVODaoImpl;
import cn.erp.dao.impl.GoodsTypeDaoImpl;
import cn.erp.domain.GoodsGoosTypeVO;
import cn.erp.domain.GoodsType;
import cn.erp.domain.Page;
import cn.erp.service.GoodsGoosTypeVOService;

public class GoodsGoosTypeVOServiceImpl implements GoodsGoosTypeVOService{

	private GoodsGoosTypeVODao goodsGoosTypeVODao =new GoodsGoosTypeVODaoImpl();
	private GoodsTypeDao goodsTypeDao = new GoodsTypeDaoImpl();
	@Override
	public List<GoodsGoosTypeVO> findAll(Page page) throws SQLException {
		List<GoodsGoosTypeVO> list = goodsGoosTypeVODao.findAll(page);
		for (GoodsGoosTypeVO goodsGoosTypeVO : list) {
			GoodsType one = goodsTypeDao.findOne(goodsGoosTypeVO.getTypeId());
			goodsGoosTypeVO.setType(one);
		}
		page.setTotal(list.size());
		return list;
	}
	@Override
	public int count() throws SQLException {
		return goodsGoosTypeVODao.count();
	}
	@Override
	public List<GoodsGoosTypeVO> findlike(Page page,String codeOrName) throws SQLException {
		List<GoodsGoosTypeVO> list = goodsGoosTypeVODao.findlike(page,codeOrName);
		for (GoodsGoosTypeVO goodsGoosTypeVO : list) {
			goodsGoosTypeVO.setType(goodsTypeDao.findOne(goodsGoosTypeVO.getTypeId()));
		}
		return list;
	}
	@Override
	public int countLike(String codeOrName) throws SQLException {
		return goodsGoosTypeVODao.countLike(codeOrName);
	}

}
