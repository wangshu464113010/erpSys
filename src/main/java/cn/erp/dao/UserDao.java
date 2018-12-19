package cn.erp.dao;

import java.sql.SQLException;

import cn.erp.domain.User;

public interface UserDao {
	User findUserById(Integer id) throws SQLException;
}
