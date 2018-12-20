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
import cn.erp.domain.GoodsType;
import cn.erp.domain.IntegerDO;
import cn.erp.domain.Page;
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

	@Override
	public int insert(Goods goods) throws SQLException {
		goods.setPurchasing_price(goods.getPurchasing_price());
		goods.setState(0);
		goods.setInventory_quantity(0);
		return goodsDao.insert(goods);
	}

	@Override
	public int update(Goods goods) throws SQLException {
		return goodsDao.update(goods);
	}

	@Override
	public int delete(int id) throws SQLException {
		return goodsDao.delete(id);
	}

	@Override
	public List<Goods> findLikeGoods(String name, int pageNow, int pageSize) throws SQLException {
		Page<Goods> page = new Page<Goods>();
		page.setPageNow(pageNow);
		page.setSize(pageSize);
		page.setTotal(goodsDao.countLikeGoods(name).getCount());
		List<Goods> list = goodsDao.findLikeGoods(name, page);
		for (Goods goods : list) {
			GoodsType findOne = goodsTypeDao.findOne(goods.getType_id());
			goods.setType(findOne);
		}
		return list;
	}

	@Override
	public int countLikeGoods(String name) throws SQLException {
		return goodsDao.countLikeGoods(name).getCount();
	}

}
