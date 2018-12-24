package cn.erp.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import cn.erp.domain.ProcurementStatisticsVO;

public interface ProcurementStatisticsVOService {
	public List<ProcurementStatisticsVO> findAll(Date bPurchaseDate,Date ePurchaseDate,String typeId,String codeOrName) throws SQLException;
}
