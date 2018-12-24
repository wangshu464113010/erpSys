package cn.erp.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.erp.domain.Page;
import cn.erp.domain.User;
import cn.erp.service.MenuService;
import cn.erp.service.UserService;
import cn.erp.service.impl.MenuServiceImpl;
import cn.erp.service.impl.UserServiceImpl;
import cn.erp.utils.LogUtils;
import cn.erp.utils.StringUtils;

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
		   }if("/list".equals(uri)){
			   String userName= request.getParameter("user.userName");
			   if(userName==null||userName.equals("")) {
				   findalluser(request,response);
			   }else {
				   findlikeUser(request,response);
			   }
			  			
			}
		   if("/save".equals(uri)){
			   insertUser(request,response);				
			}if("/delete".equals(uri)) {
				deleteUser(request,response);
			}
		
	}
    private void findlikeUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
    	String page = request.getParameter("page");
	    String rows = request.getParameter("rows");
	    String userName = request.getParameter("user.userName");
	    Page<User> page1 = new Page<User>();	
	    page1.setSize(Integer.parseInt(rows));			
	    page1.setPageNow(Integer.parseInt(page));	
		try {
			List<User> list = userService.findlikeUser(page1,userName);
			String string = JSONObject.toJSON(list).toString();
			int total = userService.count();
			string = "{\"total\":"+total+",\"rows\":"+string+"}";				
			response.getWriter().write(StringUtils.removeUnderlineAndUpperCase(string));
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//把请求响应
	}
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		try {
			userService.deleteUser(Integer.parseInt(id));		
			User u = (User) request.getSession().getAttribute("user");
			LogUtils.insertLog("删除操作", "删除增加一个用户",u.getId());
			PrintWriter pw = response.getWriter();
			pw.write("{\"success\":true}");
		} catch (NumberFormatException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
     String userName = request.getParameter("userName");
     String password = request.getParameter("password");
	 String trueName = request.getParameter("trueName");
	 String remarks = request.getParameter("remarks");
	 User user =new  User();
	 user.setUser_name(userName);
	 user.setPassword(password);
	 user.setTrue_name(trueName);
	 user.setRemarks(remarks);
	 try {
		userService.insertUser(user);
		User u = (User) request.getSession().getAttribute("user");
		LogUtils.insertLog("添加操作", "添加增加一个用户",u.getId());
		PrintWriter pw = response.getWriter();
		pw.write("{\"success\":true}");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
    private void findalluser(HttpServletRequest request, HttpServletResponse response) {
    	// TODO Auto-generated method stub
	    String page = request.getParameter("page");
	    String rows = request.getParameter("rows");
	    Page<User> page1 = new Page<User>();	
	    page1.setSize(Integer.parseInt(rows));			
	    page1.setPageNow(Integer.parseInt(page));	
		try {
			List<User> list = userService.findAllUser(page1);
			//System.out.println(list);
			String string = JSONObject.toJSON(list).toString();
			int total = userService.count();
			string = "{\"total\":"+total+",\"rows\":"+string+"}";				
			response.getWriter().write(StringUtils.removeUnderlineAndUpperCase(string));
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//把请求响应
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
			map.put("errorInfo", "ϵͳ��æ�����Ժ�����!");
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
