package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.GoodsGoosTypeVODao;
import cn.erp.domain.Goods;
import cn.erp.domain.GoodsGoosTypeVO;
import cn.erp.domain.IntegerDO;
import cn.erp.domain.Page;
import cn.erp.utils.C3P0Util;

public class GoodsGoosTypeVODaoImpl implements GoodsGoosTypeVODao{

	@Override
	public List<GoodsGoosTypeVO> findAll(Page page) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql="select g.id id,g.code code,g.name name,g.model model,g.unit unit, "
				+ " g.last_Purchasing_Price lastPurchasingPrice,g.purchasing_Price purchasingPrice, "
				+ " g.selling_Price sellingPrice,g.inventory_Quantity inventoryQuantity, "
				+ " g.min_Num minNum,g.state state,g.producer producer, "
				+ " g.remarks remarks,s.num saleTotal,t.name tname,g.type_id typeId from t_goods g,t_goodstype t "
				+ " ,t_sale_list_goods s "
				+ " where g.type_id = t.id and g.id = s.goods_id and g.inventory_quantity<=0"//条件有问题
				+ " limit ?,?";		
		return qr.query(sql, new BeanListHandler<GoodsGoosTypeVO>(GoodsGoosTypeVO.class),
				(page.getPageNow()-1)*page.getSize(),page.getSize());
	}
	@Override
	public int count() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql="select count(*) as count from t_goods g,t_goodstype t "
				+ " ,t_sale_list_goods s "
				+ " where g.type_id = t.id and g.id = s.goods_id   and g.inventory_quantity<=0	";//条件有问题
		IntegerDO query = qr.query(sql, new BeanHandler<IntegerDO>(IntegerDO.class));
		return query.getCount();
	}

	@Override
	public List<GoodsGoosTypeVO> findlike(Page page, String codeOrName) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql="select g.id id,g.code code,g.name name,g.model model,g.unit unit, "
				+ " g.last_Purchasing_Price lastPurchasingPrice,g.purchasing_Price purchasingPrice, "
				+ " g.selling_Price sellingPrice,g.inventory_Quantity inventoryQuantity, "
				+ " g.min_Num minNum,g.state state,g.producer producer, "
				+ " g.remarks remarks,s.num saleTotal,t.name tname,g.type_id typeId from t_goods g,t_goodstype t "
				+ " ,t_sale_list_goods s "
				+ " where g.type_id = t.id and g.id = s.goods_id and g.inventory_quantity<=0 "
				+ "or g.code like ? or g.name like ?"//条件有问题
				+ " limit ?,?";		
		return qr.query(sql, new BeanListHandler<GoodsGoosTypeVO>(GoodsGoosTypeVO.class),"%"+codeOrName+"%","%"+codeOrName+"%",
				(page.getPageNow()-1)*page.getSize(),page.getSize());
	}
	
	public int countLike(String codeOrName) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql="select count(*) as count from t_goods g,t_goodstype t ,t_sale_list_goods s"
				+ " where g.type_id = t.id and g.id = s.goods_id and g.inventory_quantity<=0 "
				+ "or g.code like ? or g.name like ?";//条件有问题
		IntegerDO query = qr.query(sql, new BeanHandler<IntegerDO>(IntegerDO.class),"%"+codeOrName+"%","%"+codeOrName+"%");
		return query.getCount();
	}	
}
