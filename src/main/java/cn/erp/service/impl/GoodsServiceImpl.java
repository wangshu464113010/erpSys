package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.List;

import cn.erp.dao.GoodsDao;
import cn.erp.dao.GoodsTypeDao;
import cn.erp.dao.SaleListGoodsDao;
import cn.erp.dao.impl.GoodsDaoImpl;
import cn.erp.dao.impl.GoodsTypeDaoImpl;
import cn.erp.dao.impl.SaleListGoodsDaoImpl;
import cn.erp.domain.Goods;
import cn.erp.domain.SaleListGoods;
import cn.erp.service.GoodsService;

public class GoodsServiceImpl implements GoodsService {

	private GoodsDao goodsDao = new GoodsDaoImpl();
	private GoodsTypeDao goodsTypeDao = new GoodsTypeDaoImpl();
	private SaleListGoodsDao saleListGoodsDao = new SaleListGoodsDaoImpl();
	
	@Override
	public List<Goods> findAll(Integer page,Integer rows,Integer type_id1,String codeOrName) throws SQLException {
		List<Goods> list = goodsDao.findAll(page,rows,type_id1,codeOrName);
		for (Goods goods : list) {
			Integer type_id = goods.getType_id();
			goods.setType(goodsTypeDao.findOne(type_id));
			SaleListGoods saleListGoods = saleListGoodsDao.findSaleListGoodsByGoods_Id(goods.getId());
			if(saleListGoods!=null){
				goods.setSaleTotal(saleListGoods.getNum());
			}else{
				goods.setSaleTotal(0);
			}
		}
		return list;
	}
	
	@Override
	public List<Goods> findAll() throws SQLException {
		List<Goods> list = goodsDao.findAll();
		for (Goods goods : list) {
			Integer type_id = goods.getType_id();
			goods.setType(goodsTypeDao.findOne(type_id));
		}
		return list;
	}

	@Override
	public int count() throws SQLException {
		return goodsDao.count();
	}

	@Override
	public String getMaxGoodsCode() throws SQLException {
		String code = goodsDao.getMaxGoodsCode();
		int k = Integer.parseInt(code);
		k+=1;
		String str = k + "";
		for(int i = 0 ;i<code.length();++i){
			if(str.length()>=code.length()){
				break;
			}
			str = "0"+str;
		}
		return str;
	}

}
