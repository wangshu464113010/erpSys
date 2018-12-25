package cn.erp.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.erp.domain.LogVO;
import cn.erp.domain.Page;
import cn.erp.service.LogVOService;
import cn.erp.service.impl.LogVOServiceImpl;
import cn.erp.utils.StringUtils;

/**
 * Servlet implementation class LogVOServlet
 */
//@WebServlet("/admin/log/list")
public class LogVOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LogVOService logVOService=new LogVOServiceImpl();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		Page<LogVO> page1 = new Page<LogVO>();	
		page1.setSize(Integer.parseInt(rows));			
		page1.setPageNow(Integer.parseInt(page));	
			try {
				List<LogVO> list = logVOService.FindAllLogVO(page1);
				System.out.println(list);
				String string = JSONObject.toJSON(list).toString();
				int total = logVOService.count();
				string = "{\"total\":"+total+",\"rows\":"+string+"}";				
				response.getWriter().write(StringUtils.removeUnderlineAndUpperCase(string));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//把请求响应
		
    }
	}


