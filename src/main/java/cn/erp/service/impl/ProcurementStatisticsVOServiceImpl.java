package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.Date;
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
		list = procurementStatisticsVODao.findAll(bPurchaseDate, ePurchaseDate);				
		
		for(int i =0;i<list.size();++i){
			ProcurementStatisticsVO psVO = list.get(i);
			psVO.setUser(userDao.findUserById(psVO.getUserId()));
			psVO.setSupplier(supplierDao.finddOne(psVO.getSupplierID()));
			Integer id = psVO.getId();
			
			List<PurchaseListGoodsVO> listGoods = purchaseListGoodsVODao.findByPurchaseListId(id);
			for (PurchaseListGoodsVO plgVO : listGoods) {
				Integer typeId = plgVO.getTypeId();
				plgVO.setType(goodsTypeDao.findOne(typeId));
			}
			
			Integer userId = psVO.getUserId();
			psVO.setUser(userDao.findUserById(userId));
			
			psVO.setPurchaseListGoodsList(listGoods);
		}
		return list;
	}

}
