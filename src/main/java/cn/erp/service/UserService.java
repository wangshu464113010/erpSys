package cn.erp.service;

import java.sql.SQLException;

import cn.erp.domain.User;

public interface UserService {
	public User findByUser_id(int id) throws SQLException;
}
