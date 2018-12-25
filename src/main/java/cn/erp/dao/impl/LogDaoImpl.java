package cn.erp.dao.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.LogDao;
import cn.erp.domain.Log;
import cn.erp.utils.C3P0Util;

public class LogDaoImpl implements LogDao{

	@Override
	public void insertlog(Log log) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql="insert into t_log (id,content,time,type,user_id) values (?,?,?,?,?)";
		int i  =  qr.update(sql,log.getId(),log.getContent(),log.getTime(),log.getType(),log.getUserid());
	}
	
	
	@Override
	public List<Log> findAll(String type, String btime, String etime) throws SQLException, ParseException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql ="";
		List<Log> list=new ArrayList<>();
		if(type==null||type.equals("")) {
			if(btime==null||btime.equals("")) {
				if(etime==null||etime.equals("")) {
					sql="select * from t_log  ";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class));
				}else {
					Date Etime = new java.sql.Date(format(etime).getTime());
					sql="select * from t_log  where  date_format(time,'%Y-%m-%d')= ? ";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),Etime);
				}
			}else {
				if(etime==null||etime.equals("")) {
					Date Btime = new java.sql.Date(format(btime).getTime());
					sql="select * from t_log where date_format(time,'%Y-%m-%d')= ? ";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),Btime);
				}else {
					Date Btime = new java.sql.Date(format(btime).getTime());
					Date Etime = new java.sql.Date(format(etime).getTime());
					sql="select * from t_log where  time between ? and ? ";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),Btime,Etime);
				}
			}
		}else {
			if(btime==null||btime.equals("")) {
				if(etime==null||etime.equals("")) {
					sql="select * from t_log where  type=? ";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),type);
				}else {
					Date Etime = new java.sql.Date(format(etime).getTime());
					sql="select * from t_log where  type=? and date_format(time,'%Y-%m-%d')= ? ";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),type,Etime);
				}
			}else {
				if(etime==null||etime.equals("")) {
					Date Btime = new java.sql.Date(format(btime).getTime());
					sql="select * from t_log where   type=? and date_format(time,'%Y-%m-%d')= ? ";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),type,Btime);
				}else {
					Date Btime = new java.sql.Date(format(btime).getTime());
					Date Etime = new java.sql.Date(format(etime).getTime());
					sql="select * from t_log where type=? and time between ? and ? ";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),type,Btime,Etime);
				}
			}
		}
		
		
		return list;
	}
	private static java.util.Date format(String str) throws ParseException {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    java.util.Date result = sdf.parse(str);
	    return   result;
	}
	@Override
	public List<Log> findAll(String type, String btime, String etime, int page, int rows) throws SQLException, ParseException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql ="";
		List<Log> list=new ArrayList<>();
		if(type==null||type.equals("")) {
			if(btime==null||btime.equals("")) {
				if(etime==null||etime.equals("")) {
					sql="select * from t_log  limit ?,?";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),(page-1)*rows,rows);
				}else {
					Date Etime = new java.sql.Date(format(etime).getTime());
					sql="select * from t_log  where date_format(time,'%Y-%m-%d')= ? limit ?,?";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),Etime,(page-1)*rows,rows);
				}
			}else {
				if(etime==null||etime.equals("")) {
					Date Btime = new java.sql.Date(format(btime).getTime());
					sql="select * from t_log where date_format(time,'%Y-%m-%d')= ? limit ?,?";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),Btime,(page-1)*rows,rows);
				}else {
					Date Btime = new java.sql.Date(format(btime).getTime());
					Date Etime = new java.sql.Date(format(etime).getTime());
					sql="select * from t_log where  time between ? and ? limit ?,?";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),Btime,Etime,(page-1)*rows,rows);
				}
			}
		}else {
			if(btime==null||btime.equals("")) {
				if(etime==null||etime.equals("")) {
					sql="select * from t_log where  type=? limit ?,?";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),type,(page-1)*rows,rows);
				}else {
					Date Etime = new java.sql.Date(format(etime).getTime());
					sql="select * from t_log where type=? and date_format(time,'%Y-%m-%d')= ? limit ?,?";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),type,Etime,(page-1)*rows,rows);
				}
			}else {
				if(etime==null||etime.equals("")) {
					Date Btime = new java.sql.Date(format(btime).getTime());
					sql="select * from t_log where  type=? and date_format(time,'%Y-%m-%d')= ? limit ?,?";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),type,Btime,(page-1)*rows,rows);
				}else {
					Date Btime = new java.sql.Date(format(btime).getTime());
					Date Etime = new java.sql.Date(format(etime).getTime());
					sql="select * from t_log where  type=? and time between ? and ? limit ?,?";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),type,Btime,Etime,(page-1)*rows,rows);
				}
			}
		}
		
		
		return list;
	}


}
