package cn.erp.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import cn.erp.domain.ProcurementStatisticsVO;
/**
 * 商品采购统计
 * @author wangshu
 *
 */
public interface ProcurementStatisticsVODao {
	public List<ProcurementStatisticsVO> findAll(Date bPurchaseDate,Date ePurchaseDate) throws SQLException;

	
}
