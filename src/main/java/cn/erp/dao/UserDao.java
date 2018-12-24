package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.domain.User;

public interface UserDao {
	public User findOne(int id)throws SQLException;
	public List<User> findAll()throws SQLException;
	public User findOne(String userTrueName)throws SQLException;
}
