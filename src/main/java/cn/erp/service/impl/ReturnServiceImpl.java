package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.erp.dao.GoodsTypeDao;
import cn.erp.dao.ReturnDao;
import cn.erp.dao.SupplierDao;
import cn.erp.dao.UserDao;
import cn.erp.dao.impl.GoodsTypeDaoImpl;
import cn.erp.dao.impl.ReturnDaoImpl;
import cn.erp.dao.impl.SupplierDaoImpl;
import cn.erp.dao.impl.UserDaoImpl;
import cn.erp.domain.GoodsJson;
import cn.erp.domain.GoodsType;
import cn.erp.domain.Return_List;
import cn.erp.domain.Return_List_Goods;
import cn.erp.domain.Supplier;
import cn.erp.domain.User;
import cn.erp.domain.Return;
import cn.erp.domain.ReturnList;
import cn.erp.service.ReturnService;

public class ReturnServiceImpl implements ReturnService {

	public ReturnDao returnDao = new ReturnDaoImpl();
	public UserDao userDao = new UserDaoImpl();
	public SupplierDao supplierDao = new SupplierDaoImpl();
	public GoodsTypeDao  goodsTypeDao = new GoodsTypeDaoImpl(); 
	
	@Override
	public List<ReturnList> ReturnList()throws SQLException{
		List<ReturnList> list1 =new ArrayList<>();
		List<Return> list = returnDao.findAllByP_id(-1);
		Return retrun1 = list.remove(0);
		ReturnList ReturnList=new ReturnList();
		ReturnList.setIconCls(retrun1.getIcon());
		ReturnList.setId(retrun1.getId());
		ReturnList.setName(retrun1.getName());
		ReturnList.setP_id(retrun1.getP_id());
		if(retrun1.getState()==1){
			ReturnList.setState("closed");
		}else{
			ReturnList.setState("open");
		}
		ReturnList.getAttributes().setState(retrun1.getState());;
		List<Return> listChild1 = returnDao.findAllByP_id(retrun1.getId());
		for (Return purchase1 : listChild1) {
			
			
			ReturnList returnList1=new ReturnList();
			returnList1.setIconCls(purchase1.getIcon());
			returnList1.setId(purchase1.getId());
			returnList1.setName(purchase1.getName());
			returnList1.setP_id(purchase1.getP_id());
			if(purchase1.getState()==1){
				returnList1.setState("closed");
			}else{
				returnList1.setState("open");
			}
			returnList1.getAttributes().setState(purchase1.getState());;
			ReturnList.getChildren().add(returnList1);
			List<Return> listChild2 = returnDao.findAllByP_id(purchase1.getId());
			for (Return purchase2 : listChild2) {
				ReturnList returnList2=new ReturnList();
				returnList2.setIconCls(purchase2.getIcon());
				returnList2.setId(purchase2.getId());
				returnList2.setName(purchase2.getName());
				returnList2.setP_id(purchase2.getP_id());
				if(purchase2.getState()==1){
					returnList2.setState("closed");
				}else{
					returnList2.setState("open");
				}
				returnList2.getAttributes().setState(purchase2.getState());;
				returnList1.getChildren().add(returnList2);
			}
		}
		list1.add(ReturnList);
		return list1;
	}

	@Override
	public int returnJh(int user_id, String supplier_id, float amount_payable, float amount_paid, Date return_date,
			String remarks, int state, GoodsJson goodsJson, String return_number) throws SQLException {
		int i=0;
		Return_List rl=new Return_List();
		rl.setAmount_paid(amount_paid);
		rl.setAmount_payable(amount_payable);
		rl.setReturn_date(return_date);
		rl.setReturn_number(return_number);
		rl.setRemarks(remarks);
		rl.setState(state);
		rl.setSupplier_id(Integer.parseInt(supplier_id));
		rl.setUser_id(user_id);
		i += returnDao.saveReturnList(rl);
		Return_List_Goods rlg=new Return_List_Goods();
		rlg.setCode(goodsJson.getCode());
		rlg.setGoods_id(goodsJson.getGoodsId());
		rlg.setModel(goodsJson.getModel());
		rlg.setName(goodsJson.getName());
		rlg.setNum(Integer.parseInt(goodsJson.getNum()));
		rlg.setPrice(Float.parseFloat(goodsJson.getPrice()));
		rlg.setTotal(goodsJson.getTotal());
		rlg.setType_id(goodsJson.getTypeId());
		rlg.setUnit(goodsJson.getUnit());
		Return_List return_List = returnDao.findId(return_number,Integer.parseInt(supplier_id),user_id);
		rlg.setReturn_list_id(return_List.getId());
		i += returnDao.saveReturnListGoods(rlg);
		return i;
	}

	@Override
	public List<Return_List> findAllReturnSearch(String returnNumber, Integer supplier_id, Integer state,
			String bReturnDate, String eReturnDate) throws SQLException {
		List<Return_List> selList= this.returnDao.findAllByTj(returnNumber, supplier_id, state, bReturnDate, eReturnDate);
		List<Return_List> list=new ArrayList<>();
		for (Return_List return_List : selList) {
			return_List.setSupplier(supplierDao.findSupplierById(return_List.getSupplier_id()));
			return_List.setUser(userDao.findUserById(return_List.getUser_id()));
			list.add(return_List);
		}
		return list;
	}

	@Override
	public List<Return_List_Goods> findAllListGoodsById(Integer id) throws SQLException {
		List<Return_List_Goods> list=new ArrayList<>();
		List<Return_List_Goods> returnListGoods = this.returnDao.findAllByReturnListId(id);
		Return_List return_List=new Return_List();
		if(this.returnDao.findById(id)!=null){
			return_List=this.returnDao.findById(id);
		}
		Supplier supplier = this.supplierDao.findSupplierById(return_List.getSupplier_id());
		User user = this.userDao.findUserById(return_List.getUser_id());
		GoodsType goodsType=new GoodsType();
		if(returnListGoods.size()>0){
			goodsType = this.goodsTypeDao.findOne(returnListGoods.get(0).getType_id());
		}
		return_List.setSupplier(supplier);
		return_List.setUser(user);
		for (Return_List_Goods return_List_Goods : returnListGoods) {
			return_List_Goods.setReturnList(return_List);
			return_List_Goods.setType(goodsType);
			list.add(return_List_Goods);
		}
		return list;
	}

	@Override
	public int deleteReturnList(Integer id) throws SQLException {
		int  i= this.returnDao.deleteReturnListGoodsByReturnListId(id);
		int index = this.returnDao.deleteReturnListById(id);
		return index;
	}
}
