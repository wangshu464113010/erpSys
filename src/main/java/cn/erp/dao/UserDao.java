package cn.erp.dao;

import java.sql.SQLException;

import cn.erp.domain.DamageListGoods;
import cn.erp.domain.User;

public interface UserDao {
	public User findOne(int id)throws SQLException;
}
