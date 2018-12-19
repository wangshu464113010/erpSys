package cn.erp.service;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Purchase;
import cn.erp.domain.PurchaseList;

public interface PurchaseService {
	public List<PurchaseList> purchaseList()throws SQLException;
}
