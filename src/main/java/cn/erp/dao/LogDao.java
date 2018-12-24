package cn.erp.dao;

import java.sql.SQLException;

import cn.erp.domain.Log;

public interface LogDao {
    public void insertlog(Log log) throws SQLException;//记录日志
}
