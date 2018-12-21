package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.SupplierDao;
import cn.erp.domain.Page;
import cn.erp.domain.Supplier;
import cn.erp.utils.C3P0Util;
/**
 * 
 * @author wangshu
 */
public class SupplierDaoImpl implements SupplierDao {
	
	
	@Override
	public List<Supplier> findPage(Page page) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_supplier limit ?,?";
	
		List<Supplier> query = qr.query(sql, 
				new BeanListHandler<Supplier>(Supplier.class),	
				(page.getPageNow()-1)*page.getSize(),page.getSize());
		return query;
	}
	


	@Override
	public Supplier findSupplierById(Integer id) throws SQLException {
		String sql = "select * from t_supplier where id=?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql,new BeanHandler<Supplier>(Supplier.class),id);
	}
	
	@Override
	public List<Supplier> findAll() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_supplier";
		List<Supplier> list = qr.query(sql, new BeanListHandler<Supplier>(Supplier.class));
		return list;
	}
	@Override
	public int count() throws SQLException {
		List<Supplier> findAll = findAll();
		return findAll.size();
	}
	@Override
	public Supplier finddOne(Integer id)  throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_supplier where id = ?";
		Supplier supplier = qr.query(sql, new BeanHandler<Supplier>(Supplier.class),id);
		return supplier;
	}

	@Override
	public int insert(Supplier supplier)  throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "insert into t_supplier(address,contact,name,number,remarks) values(?,?,?,?,?)";
		return qr.update(sql, supplier.getAddress(),supplier.getContact(),supplier.getName(),
				supplier.getNumber(),supplier.getRemarks());
	}

	@Override
	public int update(Supplier supplier)  throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "update t_supplier set address =?,contact =?,name=?, number=?,remarks=? where id=?";
		return qr.update(sql, supplier.getAddress(),supplier.getContact(),supplier.getName(),
				supplier.getNumber(),supplier.getRemarks(),supplier.getId());
	}

	@Override
	public int delete(Supplier supplier)  throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "delete from t_supplier where id=?";
		return qr.update(sql, supplier.getId());
	}

	@Override
	public int delete(int[] ids) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String s = "";
		for (int i : ids) {
			s = s+i+",";
		}
		String sql = "delete from t_supplier where id in ( "+s.substring(0, s.length()-1)+" )";
		//System.out.println(sql);	//------寰呬紭鍖�---------------
		return qr.update(sql);
		//return qr.update(sql,ids[0]);
	}
	@Override
	public List<Supplier> findLikePage(Page page, String name) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_supplier where name like ? limit ?,?";
		return qr.query(sql, new BeanListHandler<Supplier>(Supplier.class), "%"+name+"%",(page.getPageNow()-1)*page.getSize(),page.getSize());
	}
	@Override
	public int countLike(String name) throws SQLException {
		List<Supplier> list = findLike(name);
		return list.size();
	}
	@Override
	public List<Supplier> findLike( String name) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_supplier where name like ?";
		return qr.query(sql, new BeanListHandler<Supplier>(Supplier.class), "%"+name+"%");
	}

}
