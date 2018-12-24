package cn.erp.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import cn.erp.domain.ProcurementReturnStatisticsVO;

public interface ProcurementReturnStatisticsVODao {
	public List<ProcurementReturnStatisticsVO> findAll(Date bPurchaseDate,Date ePurchaseDate) throws SQLException ;

	public List<ProcurementReturnStatisticsVO> findAllByTypeId(Date bPurchaseDate, Date ePurchaseDate, String type_id) throws SQLException ;

}
