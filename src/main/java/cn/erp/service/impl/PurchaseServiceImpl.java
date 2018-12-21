package cn.erp.service.impl;

import java.io.ObjectOutputStream.PutField;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.erp.dao.GoodsTypeDao;
import cn.erp.dao.PurchaseDao;
import cn.erp.dao.SupplierDao;
import cn.erp.dao.UserDao;
import cn.erp.dao.impl.GoodsTypeDaoImpl;
import cn.erp.dao.impl.PurchaseDaoImpl;
import cn.erp.dao.impl.SupplierDaoImpl;
import cn.erp.dao.impl.UserDaoImpl;
import cn.erp.domain.Attribute;
import cn.erp.domain.GoodsJson;
import cn.erp.domain.GoodsType;
import cn.erp.domain.Menu;
import cn.erp.domain.Purchase;
import cn.erp.domain.PurchaseList;
import cn.erp.domain.Purchase_List;
import cn.erp.domain.Purchase_List_Goods;
import cn.erp.domain.Supplier;
import cn.erp.domain.User;
import cn.erp.service.PurchaseService;

public class PurchaseServiceImpl implements PurchaseService{
	
	public PurchaseDao purchaseDao=new PurchaseDaoImpl();
	public UserDao userDao=new UserDaoImpl();
	public SupplierDao supplierDao=new SupplierDaoImpl();
	public GoodsTypeDao goodsTypeDao=new GoodsTypeDaoImpl();
	

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


	@Override
	public int saveJh(int user_id, String supplier_id, float amount_payable, float amount_paid, Date purchase_date,
			String remarks, int state, GoodsJson goodsJson, String purchase_number) throws SQLException {
		int i=0;
		Purchase_List pl=new Purchase_List();
		pl.setAmount_paid(amount_paid);
		pl.setAmount_payable(amount_payable);
		pl.setPurchase_date(purchase_date);
		pl.setPurchase_number(purchase_number);
		pl.setRemarks(remarks);
		pl.setState(state);
		pl.setSupplier_id(Integer.parseInt(supplier_id));
		pl.setUser_id(user_id);
		i += purchaseDao.savePurchaseList(pl);
		Purchase_List_Goods plg=new Purchase_List_Goods();
		plg.setCode(goodsJson.getCode());
		plg.setGoods_id(goodsJson.getGoodsId());
		plg.setModel(goodsJson.getModel());
		plg.setName(goodsJson.getName());
		plg.setNum(Integer.parseInt(goodsJson.getNum()));
		plg.setPrice(Float.parseFloat(goodsJson.getPrice()));
		plg.setTotal(goodsJson.getTotal());
		plg.setType_id(goodsJson.getTypeId());
		plg.setUtil(goodsJson.getUnit());
		Purchase_List purchase_List = purchaseDao.findId(purchase_number,Integer.parseInt(supplier_id),user_id);
		plg.setPurchase_list_id(purchase_List.getId());
		i += purchaseDao.savePurchaseListGoods(plg);
		return i;
	}


	@Override
	public List<Purchase_List> findAllPurchaseSearch(String purchaseNumber, Integer supplier_id, Integer state,String bPurchaseDate, String ePurchaseDate) throws SQLException {
		List<Purchase_List> selList= this.purchaseDao.findAllByTj(purchaseNumber, supplier_id, state, bPurchaseDate, ePurchaseDate);
		List<Purchase_List> list=new ArrayList<>();
		for (Purchase_List purchase_List : selList) {
			purchase_List.setSupplier(supplierDao.findSupplierById(purchase_List.getSupplier_id()));
			purchase_List.setUser(userDao.findUserById(purchase_List.getUser_id()));
			list.add(purchase_List);
		}
		return list;
	}


	@Override
	public List<Purchase_List_Goods> findAllListGoodsById(Integer id) throws SQLException {
		List<Purchase_List_Goods> list=new ArrayList<>();
		List<Purchase_List_Goods> purchaseListGoods = this.purchaseDao.findAllByPurchaseListId(id);
		Purchase_List purchase_List=new Purchase_List();
		if(this.purchaseDao.findById(id)!=null){
			purchase_List=this.purchaseDao.findById(id);
		}
		Supplier supplier = this.supplierDao.findSupplierById(purchase_List.getSupplier_id());
		User user = this.userDao.findUserById(purchase_List.getUser_id());
		GoodsType goodsType=new GoodsType();
		if(purchaseListGoods.size()>0){
			goodsType = this.goodsTypeDao.findOne(purchaseListGoods.get(0).getType_id());
		}
		purchase_List.setSupplier(supplier);
		purchase_List.setUser(user);
		for (Purchase_List_Goods purchase_List_Goods : purchaseListGoods) {
			purchase_List_Goods.setPurchaseList(purchase_List);
			purchase_List_Goods.setType(goodsType);
			list.add(purchase_List_Goods);
		}
		return list;
	}


	@Override
	public int deletePurchaseList(Integer id) throws SQLException {
		int  i= this.purchaseDao.deletePuchaseListGoodsByPuchaseListId(id);
		int index = this.purchaseDao.deletePuchaseListById(id);
		return index;
	}


	@Override
	public int updataState(int id) throws SQLException {
		return purchaseDao.updataState(id);
	}
}
