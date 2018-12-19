package cn.erp.service.impl;

import java.io.ObjectOutputStream.PutField;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.erp.dao.PurchaseDao;
import cn.erp.dao.impl.PurchaseDaoImpl;
import cn.erp.domain.Attribute;
import cn.erp.domain.Menu;
import cn.erp.domain.Purchase;
import cn.erp.domain.PurchaseList;
import cn.erp.service.PurchaseService;

public class PurchaseServiceImpl implements PurchaseService{
	
	public PurchaseDao purchaseDao=new PurchaseDaoImpl();
	

	@Override
	public List<PurchaseList> purchaseList() throws SQLException {
		List<PurchaseList> list1 =new ArrayList<>();
		List<Purchase> list = purchaseDao.findAllByP_id(-1);
		Purchase purchase = list.remove(0);
		PurchaseList purchaseList=new PurchaseList();
		purchaseList.setIconCls(purchase.getIcon());
		purchaseList.setId(purchase.getId());
		purchaseList.setName(purchase.getName());
		purchaseList.setP_id(purchase.getP_id());
		if(purchase.getState()==1){
			purchaseList.setState("closed");
		}else{
			purchaseList.setState("open");
		}
		purchaseList.getAttributes().setState(purchase.getState());;
		List<Purchase> listChild1 = purchaseDao.findAllByP_id(purchase.getId());
		for (Purchase purchase1 : listChild1) {
			PurchaseList purchaseList1=new PurchaseList();
			purchaseList1.setIconCls(purchase1.getIcon());
			purchaseList1.setId(purchase1.getId());
			purchaseList1.setName(purchase1.getName());
			purchaseList1.setP_id(purchase1.getP_id());
			if(purchase1.getState()==1){
				purchaseList1.setState("closed");
			}else{
				purchaseList1.setState("open");
			}
			purchaseList1.getAttributes().setState(purchase1.getState());;
			purchaseList.getChildren().add(purchaseList1);
			List<Purchase> listChild2 = purchaseDao.findAllByP_id(purchase1.getId());
			for (Purchase purchase2 : listChild2) {
				PurchaseList purchaseList2=new PurchaseList();
				purchaseList2.setIconCls(purchase2.getIcon());
				purchaseList2.setId(purchase2.getId());
				purchaseList2.setName(purchase2.getName());
				purchaseList2.setP_id(purchase2.getP_id());
				if(purchase2.getState()==1){
					purchaseList2.setState("closed");
				}else{
					purchaseList2.setState("open");
				}
				purchaseList2.getAttributes().setState(purchase2.getState());;
				purchaseList1.getChildren().add(purchaseList2);
			}
		}
		list1.add(purchaseList);
		return list1;
	}

}
