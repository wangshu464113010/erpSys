package cn.erp.service;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Goods;
import cn.erp.domain.GoodsGoosTypeVO;
import cn.erp.domain.Page;

public interface GoodsGoosTypeVOStageService {
	List<GoodsGoosTypeVO> findAll(Page page) throws SQLException;
	public int count() throws SQLException;
	//public void insertsaveStore(Goods Goods)throws SQLException;//点击save后向goods中添加数据添加数据
	public void updatesaveStoreThree(Integer id,Integer num,Double price)throws SQLException;//通过id只更新三条数据
	public void deletesaveStore(Integer id) throws SQLException;
}
