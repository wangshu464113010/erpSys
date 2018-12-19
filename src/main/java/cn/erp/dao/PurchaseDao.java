package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Purchase;


public interface PurchaseDao {
	public List<Purchase> findAllByP_id(int p_id) throws SQLException;
}
