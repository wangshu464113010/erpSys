package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import cn.erp.dao.GoodsTypeDao;
import cn.erp.dao.ProcurementStatisticsVODao;
import cn.erp.dao.PurchaseListGoodsVODao;
import cn.erp.dao.SupplierDao;
import cn.erp.dao.UserDao;
import cn.erp.dao.impl.GoodsTypeDaoImpl;
import cn.erp.dao.impl.ProcurementStatisticsVODaoImpl;
import cn.erp.dao.impl.PurchaseListGoodsVODaoImpl;
import cn.erp.dao.impl.SupplierDaoImpl;
import cn.erp.dao.impl.UserDaoImpl;
import cn.erp.domain.ProcurementStatisticsVO;
import cn.erp.domain.PurchaseListGoodsVO;
import cn.erp.service.ProcurementStatisticsVOService;
/**
 * 采购单统计----
 * 进货----供货商
 * @author wangshu
 *
 */
public class ProcurementStatisticsVOServiceImpl implements ProcurementStatisticsVOService {
	
	private ProcurementStatisticsVODao procurementStatisticsVODao= new ProcurementStatisticsVODaoImpl();
	private UserDao userDao = new UserDaoImpl();
	private SupplierDao supplierDao = new SupplierDaoImpl();
	private PurchaseListGoodsVODao purchaseListGoodsVODao = new PurchaseListGoodsVODaoImpl();
	private GoodsTypeDao goodsTypeDao = new GoodsTypeDaoImpl();
	
	@Override
	public List<ProcurementStatisticsVO> findAll(Date bPurchaseDate,Date ePurchaseDate,String type_id,String codeOrName) throws SQLException {
		List<ProcurementStatisticsVO> list = null;
		//if((type_id==null || "".equals(type_id)) && (codeOrName ==null||codeOrName.equals("")) ){
			list = procurementStatisticsVODao.findAll(bPurchaseDate, ePurchaseDate);				
		//}
//		if((codeOrName == null||codeOrName.equals("")) ){
//			list = procurementStatisticsVODao.findAllByTypeId(bPurchaseDate, ePurchaseDate,type_id);				
//		}
		
		
		for(int i =0;i<list.size();++i){
			ProcurementStatisticsVO psVO = list.get(i);
			psVO.setUser(userDao.findUserById(psVO.getUserId()));
			psVO.setSupplier(supplierDao.finddOne(psVO.getSupplierID()));
			Integer id = psVO.getId();
			List<PurchaseListGoodsVO> listGoods = null;
			if(codeOrName!=null && !codeOrName.equals("")){//过滤codeOrName
				listGoods = purchaseListGoodsVODao.findByPurchaseListIdAndCodeOrName(id, codeOrName);
			}else{
				listGoods = purchaseListGoodsVODao.findByPurchaseListId(id);
				
			}
//			for (PurchaseListGoodsVO plgVO : listGoods) {
//				Integer typeId = plgVO.getTypeId();
//				plgVO.setType(goodsTypeDao.findOne(typeId));
//			}
			Iterator<PurchaseListGoodsVO> it = listGoods.iterator();//商品遍历
			while(it.hasNext()){
				PurchaseListGoodsVO plgVO = it.next();
				Integer typeId = plgVO.getTypeId();
				//开始对typeId进行过滤
				if(type_id==null||type_id.equals("")||type_id.equals("1")){//type_id==1查询所有
					plgVO.setType(goodsTypeDao.findOne(typeId));
					
					
					continue;
				}
				if(typeId.equals(Integer.parseInt(type_id))){
					plgVO.setType(goodsTypeDao.findOne(typeId));
				}else{
					it.remove();
				}
				//结束对typeId进行过滤
				
				
			}
			Integer userId = psVO.getUserId();
			psVO.setUser(userDao.findUserById(userId));
			
			psVO.setPurchaseListGoodsList(listGoods);
		}
		return list;
	}

}
