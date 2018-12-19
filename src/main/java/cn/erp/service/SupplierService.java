package cn.erp.service;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Supplier;

	
/**
 * 供应商Service接口
 * @author 王澍
 *
 */
public interface SupplierService {
	//查询所有
	public List<Supplier> findAll() throws SQLException;
	
	List<Supplier> findAllSupplier() throws SQLException;
	
	public Supplier findOne(int id) throws SQLException;
	//获得的数据打包成String
	public String getAllDataToString() throws SQLException;
	//分页查询
	public List<Supplier> getSupplierListForPage(int pageNow,int pageSize) throws SQLException;
	//分页---获得的数据打包成String
	public String getSupplierListForPageToString(int pageNow,int pageSize) throws SQLException;
	//模糊查询，根据公司名称
	public String likeQuerySupplierListForPageToString(int pageNow,int pageSize,String name)throws SQLException;
	public int add(Supplier supplier) throws SQLException;
	public int update(Supplier supplier) throws SQLException;
	public int delete(Supplier supplier)throws SQLException;
	public int deleteSupplierArray(int[] ids)throws SQLException;
	
}
