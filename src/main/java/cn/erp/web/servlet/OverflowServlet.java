package cn.erp.web.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.erp.domain.OverflowList;
import cn.erp.domain.OverflowListGoods;
import cn.erp.service.OverflowListGoodsService;
import cn.erp.service.OverflowListService;
import cn.erp.service.impl.OverflowListGoodsServiceImpl;
import cn.erp.service.impl.OverflowListServiceImpl;
import cn.erp.utils.StringUtils;
@WebServlet(value="/admin/overflowList/*")
public class OverflowServlet extends HttpServlet{
	
	private OverflowListService overflowListService = new OverflowListServiceImpl();
	private OverflowListGoodsService overflowListGoodsService = new OverflowListGoodsServiceImpl();
	
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/"));
		
		if("/list".equals(uri)){
			findAllOverflowList(req, resp);
		}
		if("/listGoods".equals(uri)) {
			findByOverflowNumber(req, resp);
		}
	}
	private void findByOverflowNumber(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String overflowListId = req.getParameter("overflowListId");
		int overflowListid = Integer.parseInt(overflowListId);
		List<OverflowListGoods> list = null;
		try {
			list=overflowListGoodsService.findAll(overflowListid);
			String string = JSONObject.toJSON(list).toString();
//			System.out.println(string);
			string = "{\"rows\":"+string+"}";
			string = StringUtils.removeUnderlineAndUpperCase(string);
			resp.getWriter().write(string);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void findAllOverflowList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String bOverflowDate = req.getParameter("bOverflowDate");
		String eOverflowDate = req.getParameter("eOverflowDate");
		List<OverflowList> list=null;
		try {
			Date BOverflowDate = new java.sql.Date(format(bOverflowDate).getTime());
			Date EOverflowDate = new java.sql.Date(format(eOverflowDate).getTime());
			list = overflowListService.findAll(BOverflowDate, EOverflowDate);
			String string = JSONObject.toJSON(list).toString();
			string = "{\"rows\":"+string+"}";
			string = StringUtils.removeUnderlineAndUpperCase(string);
			resp.getWriter().write(string);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static java.util.Date format(String str) throws ParseException {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    java.util.Date result = sdf.parse(str);
	    return   result;
	}
	
}
