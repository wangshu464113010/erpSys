package cn.erp.service;

import java.sql.SQLException;

import cn.erp.domain.User;

public interface UserService {
	User login(String user_name,String password) throws SQLException;
}
