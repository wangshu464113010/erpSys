package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.GoodsGoosTypeVOStageDao;
import cn.erp.domain.Goods;
import cn.erp.domain.GoodsGoosTypeVO;
import cn.erp.domain.IntegerDO;
import cn.erp.domain.Page;
import cn.erp.utils.C3P0Util;

public class GoodsGoosTypeVOStageDaoImpl implements GoodsGoosTypeVOStageDao{

	@Override
	public List<GoodsGoosTypeVO> findAll(Page page) throws SQLException {//查找出商品表所有数据
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql="select g.id id,g.code code,g.name name,g.model model,g.unit unit, " + 
				"g.last_Purchasing_Price lastPurchasingPrice,g.purchasing_Price purchasingPrice,"+ 
				"g.selling_Price sellingPrice,g.inventory_Quantity inventoryQuantity, " + 
				"g.min_Num minNum,g.state state,g.producer producer, +g.remarks remarks,t.name tname,"+ 
				"g.type_id typeId from t_goods g,t_goodstype t " + 
				"where g.inventory_quantity>0 and t.id = g.type_id limit ?,?";	
		return qr.query(sql, new BeanListHandler<GoodsGoosTypeVO>(GoodsGoosTypeVO.class),
				(page.getPageNow()-1)*page.getSize(),page.getSize());
	}

	@Override
	public int count() throws SQLException {//查找数量
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql="select count(*) as count from t_goods g,t_goodstype t "
				+ " where g.inventory_quantity>0 and t.id = g.type_id ";
		IntegerDO query = qr.query(sql, new BeanHandler<IntegerDO>(IntegerDO.class));
		return query.getCount();
	}

//	@Override
//	public void insertsaveStore(Goods goods) throws SQLException {
//		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
//		String sql="insert into t_goods (id,code,inventory_quantity,min_num,model,name,producer,purchasing_price,"
//				+ "remarks,selling_price,unit,type_id,state,last_purchasing_price) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//		int i=  qr.update(sql,goods.getId(),goods.getCode(),goods.getInventory_quantity(),goods.getMin_num(),goods.getModel(),
//				goods.getName(),goods.getProducer(),goods.getLast_purchasing_price(),goods.getRemarks(),
//				goods.getSelling_price(),goods.getUnit(),goods.getType_id(),goods.getState(),goods.getLast_purchasing_price());
//				
//	}

	@Override
	public void updatesaveStoreThree(Integer id, Integer num, Double price) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());//只拿三条数据
		String sql="update  t_goods set inventory_quantity=? ,purchasing_price = ? where id=?";
		int i = qr.update(sql,num,price,id);
	}

	@Override
	public void deletesaveStore(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());//只拿三条数据
		String sql="update  t_goods set inventory_quantity=0  where id=?";
		int i = qr.update(sql,id);
	}


}
