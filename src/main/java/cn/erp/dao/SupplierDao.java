package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Supplier;

public interface SupplierDao {
	
	List<Supplier> findAll() throws SQLException;
	
	Supplier findSupplierById(Integer id)throws SQLException;
}
