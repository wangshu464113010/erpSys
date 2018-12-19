package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import cn.erp.dao.SupplierDao;
import cn.erp.dao.impl.SupplierDaoImpl;
import cn.erp.domain.Page;
import cn.erp.domain.Supplier;
import cn.erp.service.SupplierService;

public class SupplierServiceImpl implements SupplierService {
	
	private SupplierDao supplierDao = new SupplierDaoImpl();
	
	@Override
	public List<Supplier> findAllSupplier() throws SQLException {
		return supplierDao.findAll();
	}
	@Override
	public String getSupplierListForPageToString(int pageNow, int pageSize) throws SQLException {
		Page<Supplier> page = new Page<Supplier>();
		page.setPageNow(pageNow);
		page.setSize(pageSize);
		List<Supplier> list = supplierDao.findPage(page);
		int count = supplierDao.count();
		String string = JSONObject.toJSON(list).toString();
		return "{\"total\":"+count+",\"rows\":"+string+"}";
	}
	
	@Override//like查询
	public String likeQuerySupplierListForPageToString(int pageNow, int pageSize, String name) throws SQLException {
		Page<Supplier> page = new Page<Supplier>();
		page.setPageNow(pageNow);
		page.setSize(pageSize);
		List<Supplier> list = supplierDao.findLikePage(page, name);
		int count = supplierDao.countLike(name);
		String string = JSONObject.toJSON(list).toString();
		return "{\"total\":"+count+",\"rows\":"+string+"}";
	}

	@Override
	public List<Supplier> getSupplierListForPage(int pageNow, int pageSize) throws SQLException {
		Page<Supplier> page = new Page<Supplier>();
		page.setPageNow(pageNow);
		page.setSize(pageSize);
		List<Supplier> list = supplierDao.findPage(page);
		return list;
	}
	
	
	@Override
	public String getAllDataToString() throws SQLException {
		List<Supplier> list = supplierDao.findAll();
		int count = supplierDao.count();
		String string = JSONObject.toJSON(list).toString();
		string = "{\"total\":"+count+",\"rows\":"+string+"}";
		return string;
	}
	
	@Override
	public List<Supplier> findAll() throws SQLException {
		return supplierDao.findAll();
	}

	@Override
	public Supplier findOne(int id) throws SQLException {
		return supplierDao.finddOne(id);
	}

	@Override
	public int add(Supplier supplier) throws SQLException {
		return supplierDao.insert(supplier);
	}

	@Override
	public int update(Supplier supplier) throws SQLException {
		return supplierDao.update(supplier);
	}

	@Override
	public int delete(Supplier supplier) throws SQLException {
		return supplierDao.delete(supplier);
	}

	@Override
	public int deleteSupplierArray(int[] ids) throws SQLException {
		return supplierDao.delete(ids);
	}


}
