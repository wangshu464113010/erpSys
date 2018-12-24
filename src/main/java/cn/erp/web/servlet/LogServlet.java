package cn.erp.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogServlet
 */

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.erp.domain.Log;
import cn.erp.service.LogService;
import cn.erp.service.impl.LogServiceImpl;
import cn.erp.utils.StringUtils;
@WebServlet(value="/admin/log/*")
public class LogServlet extends HttpServlet{
	private LogService logService = new LogServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/"));
		if ("/list".equals(uri)) {
			String type = req.getParameter("type");
			String userTrueName = req.getParameter("user.trueName");
			String btime = req.getParameter("btime");
			String etime = req.getParameter("etime");
			String pages = req.getParameter("page");
			String row = req.getParameter("rows");
			int page = Integer.parseInt(pages);
			int rows = Integer.parseInt(row);
			List<Log> list = null;
			try {
				
				list = logService.findByAll(type, btime, etime, page, rows, userTrueName);
				int total =	list.size();
				String string = JSONObject.toJSON(list).toString();
				string = "{\"total\":"+total+",\"rows\":"+string+"}";
				string = StringUtils.removeUnderlineAndUpperCase(string);
				resp.getWriter().write(string);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
