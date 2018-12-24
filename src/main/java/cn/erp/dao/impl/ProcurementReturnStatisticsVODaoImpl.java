package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.ProcurementReturnStatisticsVODao;
import cn.erp.domain.ProcurementReturnStatisticsVO;
import cn.erp.domain.ProcurementStatisticsVO;
import cn.erp.utils.C3P0Util;

public class ProcurementReturnStatisticsVODaoImpl implements ProcurementReturnStatisticsVODao {

	@Override
	public List<ProcurementReturnStatisticsVO> findAll(Date bPurchaseDate, Date ePurchaseDate) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select tpl.id id,tpl.return_Number returnNumber,"+
		" tpl.return_Date returnDate,tpl.amount_Payable amountPayable,"+
		" tpl.amount_Paid amountPaid,tpl.state state,tpl.remarks remarks,"+
		" tpl.supplier_id supplierId,tpl.user_id userId "+
		" from "+
		" t_return_list tpl where tpl.return_Date >=? and tpl.return_Date <=?";
		return qr.query(sql, 
				new BeanListHandler<ProcurementReturnStatisticsVO>(ProcurementReturnStatisticsVO.class)
				,bPurchaseDate,ePurchaseDate);
	}

	@Override
	public List<ProcurementReturnStatisticsVO> findAllByTypeId(Date bPurchaseDate, Date ePurchaseDate, String type_id)
			throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select tpl.id id,tpl.return_Number returnNumber,"+
		" tpl.return_Date returnDate,tpl.amount_Payable amountPayable,"+
		" tpl.amount_Paid amountPaid,tpl.state state,tpl.remarks remarks,"+
		" tpl.supplier_id supplierId,tpl.user_id userId "+
		" from "+
		" t_return_list tpl where tpl.return_Date >=? and tpl.return_Date <=?";
		return qr.query(sql, 
				new BeanListHandler<ProcurementReturnStatisticsVO>(ProcurementReturnStatisticsVO.class)
				,bPurchaseDate,ePurchaseDate);
	}

}
