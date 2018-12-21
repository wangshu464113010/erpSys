package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Goods;
import cn.erp.domain.GoodsGoosTypeVO;
import cn.erp.domain.Page;

public interface GoodsGoosTypeVOStageDao {
	public List<GoodsGoosTypeVO> findAll(Page page) throws SQLException;//查询所有数据
	public int count() throws SQLException;//查询数量
	public void updatesaveStoreThree(Integer id,Integer num,Double price)throws SQLException;//通过id只更新三条数据
	public void deletesaveStore(Integer id) throws SQLException;//删除Storge的库存
}
