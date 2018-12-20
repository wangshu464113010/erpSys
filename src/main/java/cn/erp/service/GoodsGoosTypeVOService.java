package cn.erp.service;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.GoodsGoosTypeVO;
import cn.erp.domain.Page;

public interface GoodsGoosTypeVOService {
	List<GoodsGoosTypeVO> findAll(Page page) throws SQLException;
	public int count() throws SQLException;
	public List<GoodsGoosTypeVO>findlike(Page page ,String codeOrName)throws SQLException;
	public int countLike(String codeOrName) throws SQLException;
}
