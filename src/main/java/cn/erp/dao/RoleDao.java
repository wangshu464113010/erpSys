package cn.erp.dao;

import java.sql.SQLException;
import java.util.List;

import cn.erp.dao.impl.User_Role;

public interface RoleDao {
	List<User_Role> findByUserId(Integer id)throws SQLException;
}
