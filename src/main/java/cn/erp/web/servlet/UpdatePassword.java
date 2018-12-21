package cn.erp.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.erp.domain.User;
import cn.erp.service.MenuService;
import cn.erp.service.UserService;
import cn.erp.service.impl.MenuServiceImpl;
import cn.erp.service.impl.UserServiceImpl;

@WebServlet("/admin/user/*")
public class UpdatePassword extends HttpServlet{
private static final long serialVersionUID = 1L;
	
    private UserService userService = new UserServiceImpl();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/"));
		if("/modifyPassword".equals(uri)){
			updatePassword(request,response);
		}
		if("/logout".equals(uri)){
			logout(request,response);
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void updatePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		response.setCharacterEncoding("utf-8");
		String newPassword = request.getParameter("newPassword");
		User user = (User)request.getSession().getAttribute("user");
		Map<String ,Object> map=new HashMap<>();
		int i=0;
		try {
			i += this.userService.updatePassword(user, newPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(i>0){
			map.put("success", true);
			request.getSession().removeAttribute("user");
		}else{
			map.put("success", false);
			map.put("errorInfo", "系统繁忙，请稍后再试!");
		}
		pw.write(JSONObject.toJSON(map).toString());
	}
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		response.setCharacterEncoding("utf-8");
		request.getSession().removeAttribute("user");
		response.sendRedirect("/login.html");
	}
	
	
	
}
