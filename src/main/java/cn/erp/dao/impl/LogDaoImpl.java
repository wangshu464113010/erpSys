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

	public List<Log> findAll(String type, String btime, String etime, int page, int rows,int user_id) throws SQLException, ParseException {
//		Date Btime = new java.sql.Date(format(btime).getTime());
//		Date Etime = new java.sql.Date(format(etime).getTime());
		
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql ="";
		List<Log> list=new ArrayList<>();
		if(type==null||type.equals("")) {
			if(btime==null||btime.equals("")) {
				if(etime==null||etime.equals("")) {
					sql="select * from t_log where  user_id=? limit ?,?";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),user_id,(page-1)*rows,rows);
				}else {
					Date Etime = new java.sql.Date(format(etime).getTime());
					sql="select * from t_log  where user_id=? and date_format(time,'%Y-%m-%d')= ? limit ?,?";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),user_id,Etime,(page-1)*rows,rows);
				}
			}else {
				if(etime==null||etime.equals("")) {
					Date Btime = new java.sql.Date(format(btime).getTime());
					sql="select * from t_log where user_id=? and date_format(time,'%Y-%m-%d')= ? limit ?,?";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),user_id,Btime,(page-1)*rows,rows);
				}else {
					Date Btime = new java.sql.Date(format(btime).getTime());
					Date Etime = new java.sql.Date(format(etime).getTime());
					sql="select * from t_log where user_id=? and time between ? and ? limit ?,?";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),user_id,Btime,Etime,(page-1)*rows,rows);
				}
			}
		}else {
			if(btime==null||btime.equals("")) {
				if(etime==null||etime.equals("")) {
					sql="select * from t_log where user_id=? and type=? limit ?,?";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),user_id,type,(page-1)*rows,rows);
				}else {
					Date Etime = new java.sql.Date(format(etime).getTime());
					sql="select * from t_log where user_id=? and type=? and date_format(time,'%Y-%m-%d')= ? limit ?,?";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),user_id,type,Etime,(page-1)*rows,rows);
				}
			}else {
				if(etime==null||etime.equals("")) {
					Date Btime = new java.sql.Date(format(btime).getTime());
					sql="select * from t_log where user_id=? and type=? and date_format(time,'%Y-%m-%d')= ? limit ?,?";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),user_id,type,Btime,(page-1)*rows,rows);
				}else {
					Date Btime = new java.sql.Date(format(btime).getTime());
					Date Etime = new java.sql.Date(format(etime).getTime());
					sql="select * from t_log where user_id=? and type=? and time between ? and ? limit ?,?";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),user_id,type,Btime,Etime,(page-1)*rows,rows);
				}
			}
		}
		
		
		return list;
	}

	
	
	


	@Override
	public List<Log> findAll(String type, String btime, String etime,int user_id) throws SQLException, ParseException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql ="";
		List<Log> list=new ArrayList<>();
		if(type==null||type.equals("")) {
			if(btime==null||btime.equals("")) {
				if(etime==null||etime.equals("")) {
					sql="select * from t_log where user_id=? ";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),user_id);
				}else {
					Date Etime = new java.sql.Date(format(etime).getTime());
					sql="select * from t_log  where user_id=? and date_format(time,'%Y-%m-%d')= ? ";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),user_id,Etime);
				}
			}else {
				if(etime==null||etime.equals("")) {
					Date Btime = new java.sql.Date(format(btime).getTime());
					sql="select * from t_log where user_id=? and date_format(time,'%Y-%m-%d')= ? ";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),user_id,Btime);
				}else {
					Date Btime = new java.sql.Date(format(btime).getTime());
					Date Etime = new java.sql.Date(format(etime).getTime());
					sql="select * from t_log where user_id=? and time between ? and ? ";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),user_id,Btime,Etime);
				}
			}
		}else {
			if(btime==null||btime.equals("")) {
				if(etime==null||etime.equals("")) {
					sql="select * from t_log where user_id=? and type=? ";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),user_id,type);
				}else {
					Date Etime = new java.sql.Date(format(etime).getTime());
					sql="select * from t_log where user_id=? and type=? and date_format(time,'%Y-%m-%d')= ? ";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),user_id,type,Etime);
				}
			}else {
				if(etime==null||etime.equals("")) {
					Date Btime = new java.sql.Date(format(btime).getTime());
					sql="select * from t_log where user_id=? and type=? and date_format(time,'%Y-%m-%d')= ? ";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),user_id,type,Btime);
				}else {
					Date Btime = new java.sql.Date(format(btime).getTime());
					Date Etime = new java.sql.Date(format(etime).getTime());
					sql="select * from t_log where user_id=?  and type=? and time between ? and ? ";
					list =  qr.query(sql,new BeanListHandler<Log>(Log.class),user_id,type,Btime,Etime);
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


}
