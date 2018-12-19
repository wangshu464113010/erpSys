package cn.erp.service;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Supplier;

public interface SupplierService {
	List<Supplier> findAllSupplier() throws SQLException;
}
