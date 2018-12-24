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
import cn.erp.utils.LogUtils;

@WebServlet("/admin/user/*")
public class UpdatePassword extends HttpServlet{
private static final long serialVersionUID = 1L;
	
    private UserService userService = new UserServiceImpl();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/"));
		if("/modifyPassword".equals(uri)){
			try {
				updatePassword(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("/logout".equals(uri)){
			try {
				logout(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void updatePassword(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
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
			User u = (User) request.getSession().getAttribute("user");
			LogUtils.insertLog("更新操作", "密码修改成功",u.getId());
		}else{
			map.put("success", false);
			map.put("errorInfo", "修改失敗!");
		}
		pw.write(JSONObject.toJSON(map).toString());
	}
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		PrintWriter pw = response.getWriter();
		response.setCharacterEncoding("utf-8");
		request.getSession().removeAttribute("user");
		response.sendRedirect("/login.html");
		User u = (User) request.getSession().getAttribute("user");
		LogUtils.insertLog("注销操作", "注销成功",u.getId());
	}
	
	
	
}
