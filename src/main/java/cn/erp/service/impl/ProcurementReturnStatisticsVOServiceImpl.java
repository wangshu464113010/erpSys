package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import cn.erp.dao.GoodsTypeDao;
import cn.erp.dao.ProcurementReturnStatisticsVODao;
import cn.erp.dao.ReturnListGoodsVODao;
import cn.erp.dao.SupplierDao;
import cn.erp.dao.UserDao;
import cn.erp.dao.impl.GoodsTypeDaoImpl;
import cn.erp.dao.impl.ProcurementReturnStatisticsVODaoImpl;
import cn.erp.dao.impl.ReturnListGoodsVODaoImpl;
import cn.erp.dao.impl.SupplierDaoImpl;
import cn.erp.dao.impl.UserDaoImpl;
import cn.erp.domain.ProcurementReturnStatisticsVO;
import cn.erp.domain.ReturnListGoodsVO;
import cn.erp.service.ProcurementReturnStatisticsVOService;

public class ProcurementReturnStatisticsVOServiceImpl implements ProcurementReturnStatisticsVOService {
	private ProcurementReturnStatisticsVODao procurementReturnStatisticsVODao= new ProcurementReturnStatisticsVODaoImpl();
	private UserDao userDao = new UserDaoImpl();
	private SupplierDao supplierDao = new SupplierDaoImpl();
	private ReturnListGoodsVODao returnListGoodsVODao = new ReturnListGoodsVODaoImpl();
	private GoodsTypeDao goodsTypeDao = new GoodsTypeDaoImpl();
	
	@Override
	public List<ProcurementReturnStatisticsVO> findAll(Date bPurchaseDate, Date ePurchaseDate, String type_id,
			String codeOrName) throws SQLException {
		List<ProcurementReturnStatisticsVO> list = null;
			list = procurementReturnStatisticsVODao.findAll(bPurchaseDate, ePurchaseDate);				
		
		
		for(int i =0;i<list.size();++i){
			ProcurementReturnStatisticsVO psVO = list.get(i);
			psVO.setUser(userDao.findUserById(psVO.getUserId()));
			psVO.setSupplier(supplierDao.finddOne(psVO.getSupplierID()));
			Integer id = psVO.getId();
			List<ReturnListGoodsVO> listGoods = null;
			if(codeOrName!=null && !codeOrName.equals("")){//过滤codeOrName
				listGoods = returnListGoodsVODao.findByPurchaseListIdAndCodeOrName(id, codeOrName);
			}else{
				listGoods = returnListGoodsVODao.findByPurchaseListId(id);
			}
			Iterator<ReturnListGoodsVO> it = listGoods.iterator();//商品遍历
			while(it.hasNext()){
				ReturnListGoodsVO plgVO = it.next();
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
			psVO.setReturnListGoodsList(listGoods);
			//psVO.setPurchaseListGoodsList(listGoods);
		}
		return list;
	}

}
