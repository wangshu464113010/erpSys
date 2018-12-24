package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.LogVO;
import cn.erp.domain.Page;

public interface LogVODao {
	List<LogVO>FindAllLogVO(Page page) throws SQLException;//没有条件时，查找所有的数据
	public int count() throws SQLException;//查询数量
}
