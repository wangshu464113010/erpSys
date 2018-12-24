package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.ProcurementStatisticsVODao;
import cn.erp.domain.ProcurementStatisticsVO;
import cn.erp.utils.C3P0Util;

public class ProcurementStatisticsVODaoImpl implements ProcurementStatisticsVODao {

	@Override
	public List<ProcurementStatisticsVO> findAll(Date bPurchaseDate,Date ePurchaseDate) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select tpl.id id,tpl.purchase_Number purchaseNumber,"+
		" tpl.purchase_Date purchaseDate,tpl.amount_Payable amountPayable,"+
		" tpl.amount_Paid amountPaid,tpl.state state,tpl.remarks remarks,"+
		" tpl.supplier_id supplierId,tpl.user_id userId "+
		" from "+
		" t_purchase_list tpl where tpl.purchase_Date >=? and tpl.purchase_Date <=?";
		return qr.query(sql, 
				new BeanListHandler<ProcurementStatisticsVO>(ProcurementStatisticsVO.class)
				,bPurchaseDate,ePurchaseDate);
	}

	@Override
	public List<ProcurementStatisticsVO> findAllByTypeId(Date bPurchaseDate, Date ePurchaseDate, String type_id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select tpl.id id,tpl.purchase_Number purchaseNumber,"+
		" tpl.purchase_Date purchaseDate,tpl.amount_Payable amountPayable,"+
		" tpl.amount_Paid amountPaid,tpl.state state,tpl.remarks remarks,"+
		" tpl.supplier_id supplierId,tpl.user_id userId "+
		" from "+
		" t_purchase_list tpl where  and tpl.purchase_Date >=? and tpl.purchase_Date <=?";
		return qr.query(sql, 
				new BeanListHandler<ProcurementStatisticsVO>(ProcurementStatisticsVO.class)
				,bPurchaseDate,ePurchaseDate);
	}

}
