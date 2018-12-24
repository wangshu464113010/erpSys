package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.erp.dao.CustomerRetrunListGoodsDao;
import cn.erp.domain.CustomerReturnListGoods;
import cn.erp.utils.C3P0Util;

public class CustomerRetrunListGoodsDaoImpl implements CustomerRetrunListGoodsDao{
	@Override
	public int insertCustomerRetrunListGoods(CustomerReturnListGoods customerReturnListGoods) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "";
		sql = "select max(id) from t_customer_return_list";
        Integer customer_return_list_id = (Integer) qr.query(sql,new ScalarHandler());
		sql = "insert into t_customer_return_list_goods values(default,?,?,?,?,?,?,?,?,?,?)";
		int i = qr.update(sql,customerReturnListGoods.getCode(),customerReturnListGoods.getModel(),customerReturnListGoods.getName(),
				customerReturnListGoods.getNum(),customerReturnListGoods.getPrice(),
				customerReturnListGoods.getTotal(),customerReturnListGoods.getUnit(),
				customer_return_list_id,
				customerReturnListGoods.getType_id(),customerReturnListGoods.getGoods_id());
		return i;
	}
	
	@Override
	public List<CustomerReturnListGoods> findByCustomerReturnListId(Integer customerReturnListId,Integer type_id,String codeOrName) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql =  "";
		List<CustomerReturnListGoods> list = null;
		System.out.println(customerReturnListId);
		if(type_id==null&&("".equals(codeOrName)||codeOrName==null)){
			sql = "select * from t_customer_return_list_goods where customer_return_list_id=?";
			list =  qr.query(sql,new BeanListHandler<CustomerReturnListGoods>(CustomerReturnListGoods.class),customerReturnListId);
		}else if(type_id==null&&(!"".equals(codeOrName)||codeOrName!=null)){
			sql = "select * from t_customer_return_list_goods where customer_return_list_id=? and code like ? or name like ?";
			list = qr.query(sql, new BeanListHandler<CustomerReturnListGoods>(CustomerReturnListGoods.class),customerReturnListId,"%"+codeOrName+"%","%"+codeOrName+"%");
		}else if(type_id!=null&&("".equals(codeOrName)||codeOrName==null)){
			sql = "select * from t_customer_return_list_goods where customer_return_list_id=? and type_id=?";
			list =  qr.query(sql,new BeanListHandler<CustomerReturnListGoods>(CustomerReturnListGoods.class),customerReturnListId,type_id);
		}else if(type_id!=null&&(!"".equals(codeOrName)||codeOrName!=null)){
			sql = "select * from t_customer_return_list_goods where customer_return_list_id=? and type_id=? and code like ? or name like ?";
			list = qr.query(sql, new BeanListHandler<CustomerReturnListGoods>(CustomerReturnListGoods.class),customerReturnListId,type_id,"%"+codeOrName+"%","%"+codeOrName+"%");
		}
		return list;
	}
}
