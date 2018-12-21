package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.List;

import cn.erp.dao.GoodsGoosTypeVODao;
import cn.erp.dao.GoodsGoosTypeVOStageDao;
import cn.erp.dao.GoodsTypeDao;
import cn.erp.dao.impl.GoodsGoosTypeVODaoImpl;
import cn.erp.dao.impl.GoodsGoosTypeVOStageDaoImpl;
import cn.erp.dao.impl.GoodsTypeDaoImpl;
import cn.erp.domain.Goods;
import cn.erp.domain.GoodsGoosTypeVO;
import cn.erp.domain.GoodsType;
import cn.erp.domain.Page;
import cn.erp.service.GoodsGoosTypeVOStageService;

public class GoodsGoosTypeVOStageServiceImpl implements GoodsGoosTypeVOStageService{
    private GoodsGoosTypeVOStageDao goodsGoosTypeVOStageDao =new GoodsGoosTypeVOStageDaoImpl();
	private GoodsTypeDao goodsTypeDao = new GoodsTypeDaoImpl();
	@Override
	public List<GoodsGoosTypeVO> findAll(Page page) throws SQLException {
		List<GoodsGoosTypeVO> list = goodsGoosTypeVOStageDao.findAll(page);
		for (GoodsGoosTypeVO goodsGoosTypeVO : list) {
			GoodsType one = goodsTypeDao.findOne(goodsGoosTypeVO.getTypeId());
			goodsGoosTypeVO.setType(one);
		}
		page.setTotal(list.size());
		
		return list;
	}

	@Override
	public int count() throws SQLException {
		return goodsGoosTypeVOStageDao.count();
	}

//	@Override
//	public void insertsaveStore(Goods goods) throws SQLException {
//		// goodsGoosTypeVOStageDao.insertsaveStore(goods);
//		
//	}

	@Override
	public void updatesaveStoreThree(Integer id, Integer num, Double price) throws SQLException {
		// TODO Auto-generated method stub
		goodsGoosTypeVOStageDao.updatesaveStoreThree(id, num, price);
	}

	@Override
	public void deletesaveStore(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		goodsGoosTypeVOStageDao.deletesaveStore(id);
	}

}
