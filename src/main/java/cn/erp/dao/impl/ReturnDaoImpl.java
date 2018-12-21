package cn.erp.dao.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.domain.Return_List;
import cn.erp.domain.Return_List_Goods;
import cn.erp.dao.ReturnDao;
import cn.erp.domain.Return;
import cn.erp.utils.C3P0Util;

public class ReturnDaoImpl implements ReturnDao {

	@Override
	public List<Return> findAllByP_id(int p_id) throws SQLException {
		String sql = "select * from t_goodstype where p_id=?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql, new BeanListHandler<Return>(Return.class), p_id);
	}

	@Override
	public int saveReturnListGoods(Return_List_Goods rlg) throws SQLException {
		String sql = "insert into t_return_list_goods values(default,?,?,?,?,?,?,?,?,?,?)";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.update(sql, rlg.getCode(), rlg.getModel(), rlg.getName(), rlg.getNum(), rlg.getPrice(),
				rlg.getTotal(), rlg.getUnit(), rlg.getReturn_list_id(), rlg.getType_id(), rlg.getGoods_id());
	}

	@Override
	public int saveReturnList(Return_List rl) throws SQLException {
		String sql = "insert into t_return_list values(default,?,?,?,?,?,?,?,?)";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.update(sql, rl.getAmount_paid(), rl.getAmount_payable(),
				 rl.getRemarks(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rl.getReturn_date()),
				rl.getReturn_number(),rl.getState(), rl.getSupplier_id(), rl.getUser_id());
	}

	@Override
	public Return_List findId(String return_number, Integer supplier_id, Integer user_id) throws SQLException {
		String sql = "select * from t_return_list where return_number=? and supplier_id=? and user_id=?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql, new BeanHandler<Return_List>(Return_List.class), return_number, supplier_id, user_id);
	}

	@Override
	public List<Return_List> findAllByTj(String returnNumber, Integer supplier_id, Integer state, String bReturnDate,
			String eReturnDate) throws SQLException {
		if (state != null) {
			if (supplier_id != null) {
				String sql = "select * from t_return_list where return_number like ? and supplier_id = ? and state = ? and return_date >= ? and  return_date <=?";
				QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
				return qr.query(sql, new BeanListHandler<Return_List>(Return_List.class), "%" + returnNumber + "%",
						supplier_id, state, bReturnDate, eReturnDate);
			} else {
				String sql = "select * from t_return_list where return_number like ? and state = ? and return_date >= ? and  return_date <=?";
				QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
				return qr.query(sql, new BeanListHandler<Return_List>(Return_List.class), "%" + returnNumber + "%",
						state, bReturnDate, eReturnDate);
			}

		} else {
			if (supplier_id != null) {
				String sql = "select * from t_return_list where return_number like ? and supplier_id = ? and return_date >= ? and   return_date <=?";
				QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
				return qr.query(sql, new BeanListHandler<Return_List>(Return_List.class), "%" + returnNumber + "%",
						supplier_id, bReturnDate, eReturnDate);
			} else {
				String sql = "select * from t_return_list where return_number like ? and return_date >= ? and  return_date <=?";
				QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
				return qr.query(sql, new BeanListHandler<Return_List>(Return_List.class), "%" + returnNumber + "%",
						bReturnDate, eReturnDate);
			}

		}
	}

	@Override
	public List<Return_List_Goods> findAllByReturnListId(Integer id) throws SQLException {
		String sql = "select * from t_return_list_goods where return_list_id = ?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql, new BeanListHandler<Return_List_Goods>(Return_List_Goods.class), id);
	}

	@Override
	public Return_List findById(Integer id) throws SQLException {
		String sql = "select * from t_return_list where id = ?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql, new BeanHandler<Return_List>(Return_List.class), id);
	}

	@Override
	public int deleteReturnListById(Integer id) throws SQLException {
		String sql = "delete from t_return_list where id=?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.update(sql, id);
	}

	@Override
	public int deleteReturnListGoodsByReturnListId(Integer id) throws SQLException {
		String sql = "delete from t_return_list_goods where return_list_id=?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.update(sql, id);
	}

}
