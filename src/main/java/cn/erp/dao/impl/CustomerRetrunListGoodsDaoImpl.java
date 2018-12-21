package cn.erp.dao.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.erp.dao.CustomerRetrunListGoodsDao;
import cn.erp.domain.CustomerReturnList;
import cn.erp.domain.CustomerReturnListGoods;
import cn.erp.domain.SaleListGoods;
import cn.erp.domain.User;
import cn.erp.utils.C3P0Util;
import cn.erp.utils.StringUtils;

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
}
