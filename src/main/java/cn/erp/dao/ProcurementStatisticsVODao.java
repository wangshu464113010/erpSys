package cn.erp.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import cn.erp.domain.ProcurementStatisticsVO;
/**
 * ��Ʒ�ɹ�ͳ��
 * @author wangshu
 *
 */
public interface ProcurementStatisticsVODao {
	public List<ProcurementStatisticsVO> findAll(Date bPurchaseDate,Date ePurchaseDate) throws SQLException;

	
}
