package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.SupplierDao;
import cn.erp.domain.Purchase;
import cn.erp.domain.Supplier;
import cn.erp.domain.User;
import cn.erp.utils.C3P0Util;

public class SupplierDaoImpl implements SupplierDao{

	@Override
	public List<Supplier> findAll() throws SQLException {
		String sql = "select * from t_supplier";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql,new BeanListHandler<Supplier>(Supplier.class));
	}

	@Override
	public Supplier findSupplierById(Integer id) throws SQLException {
		String sql = "select * from t_supplier where id=?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql,new BeanHandler<Supplier>(Supplier.class),id);
	}

}
