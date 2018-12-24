package cn.erp.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.erp.domain.Menu;
import cn.erp.domain.Page;
import cn.erp.domain.User;
import cn.erp.service.MenuService;
import cn.erp.service.UserService;
import cn.erp.service.impl.MenuServiceImpl;
import cn.erp.service.impl.UserServiceImpl;
import cn.erp.utils.LogUtils;
import cn.erp.utils.StringUtils;

@WebServlet("/user/*")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private MenuService menuService = new MenuServiceImpl();
    private UserService userService = new UserServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/"));
		if("/loadMenuInfo".equals(uri)){
			findAll(request,response);//
			
		}
		if("/login".equals(uri)){
			login(request,response);
			
		}
		if("/loadUserInfo".equals(uri)){
			load(request,response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		User user = (User)request.getSession().getAttribute("user");
		try {
			List<Menu> list = menuService.menuList(user.getId());
			Object json = JSONObject.toJSON(list);
			pw.write(json.toString().replaceAll("name", "text"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		String user_name = request.getParameter("userName");
		String password = request.getParameter("password");
		try {
			Map<String ,Object> map=new HashMap<>();
			if("".equals(user_name)||user_name==null){
				map.put("success", false);
				map.put("errorInfo", 1);
			}else if("".equals(password)||password==null){
				map.put("success", false);
				map.put("errorInfo", 2);
			}else{
				User user = this.userService.login(user_name, password);
				if(user==null){
					map.put("success", false);
					map.put("errorInfo", 3);
				}else{
					request.getSession().setAttribute("user", user);
					map.put("success", true);
					User u = (User) request.getSession().getAttribute("user");
					LogUtils.insertLog("登录操作", "用户登录",u.getId());
				}	
			}
			pw.write(JSONObject.toJSON(map).toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	private void load(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		response.setCharacterEncoding("utf-8");
		User user = (User)request.getSession().getAttribute("user");
		pw.write("welcome:"+user.getTrue_name()+",you are:"+user.getBz());
	}

}
