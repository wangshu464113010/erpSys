package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.Page;
import cn.erp.domain.Supplier;
/**
 * 供应商的dao层接口
 * @author wangshu 
 *
 */
public interface SupplierDao {
	//查询所有
	public List<Supplier> findAll() throws SQLException;
	//查找一个,根据id
	public Supplier finddOne(Integer id)throws SQLException;
	//对数据库中所有供应商的数量计数
	public int count()throws SQLException;
	//给定条件，对数据库中的满足条件的供应商计数
	public int countLike(String name)throws SQLException;
	//分页查询
	public List<Supplier> findPage(Page page) throws SQLException;
	//模糊查询，根据公司名称
	public List<Supplier> findLikePage(Page page ,String name)throws SQLException;
	//按条件模糊查询所有供应商
	public List<Supplier> findLike(String name) throws SQLException ;
	/** 增删改*/
	public int insert(Supplier supplier)throws SQLException;
	
	public int update(Supplier supplier)throws SQLException;
	
	public int delete(Supplier supplier)throws SQLException;
	/**根据一个数组(主键)删除 */
	public int delete(int[] ids)throws SQLException;
	
}
