package cn.erp.service;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.LogVO;
import cn.erp.domain.Page;

public interface LogVOService {
	List<LogVO>FindAllLogVO(Page page) throws SQLException;
	public int count() throws SQLException;//查询数量
}
