package cn.erp.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.erp.domain.Menu;
import cn.erp.service.MenuService;
import cn.erp.service.impl.MenuServiceImpl;

@WebServlet("/user/*")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private MenuService menuService = new MenuServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/"));
		if("/loadMenuInfo".equals(uri)){
			findAll(request,response);//
		}
		
		
	}
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String parentId = request.getParameter("parentId");
		PrintWriter pw = response.getWriter();
		try {
			List<Menu> list = menuService.menuList();
			Object json = JSONObject.toJSON(list);
			pw.write(json.toString().replaceAll("name", "text"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
