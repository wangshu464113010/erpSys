package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.List;

import cn.erp.dao.SupplierDao;
import cn.erp.dao.impl.SupplierDaoImpl;
import cn.erp.domain.Supplier;
import cn.erp.service.SupplierService;

public class SupplierServiceImpl implements SupplierService{

	private SupplierDao supplierDao=new SupplierDaoImpl();
	
	@Override
	public List<Supplier> findAllSupplier() throws SQLException {
		return supplierDao.findAll();
	}

}
