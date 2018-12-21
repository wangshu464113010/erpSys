package cn.erp.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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

import com.alibaba.fastjson.JSONObject;

import cn.erp.domain.Role;
import cn.erp.service.RoleService;
import cn.erp.service.impl.RoleServiceImpl;


@WebServlet("/admin/role/*")
public class RoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private RoleService roleService=new RoleServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/"));
		if("/list".equals(uri)){
			findAllRole(request,response);//
		}
		if("/save".equals(uri)){
			saleRole(request,response);//
		}
		if("/delete".equals(uri)){
			deleteRole(request,response);
		}
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void findAllRole(HttpServletRequest request, HttpServletResponse response){
		
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer rows = Integer.parseInt(request.getParameter("rows"));
		try {
			PrintWriter pw = response.getWriter();
			List<Role> list = this.roleService.findAllRoleFy(page, rows);
			List<Role> list2 = this.roleService.findAllRole();
			int total = list2.size();
			Map<String,Object> map=new HashMap<>();
			map.put("total", total);
			map.put("rows", list);
			pw.write(JSONObject.toJSON(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void saleRole(HttpServletRequest request, HttpServletResponse response){
		
		String name = request.getParameter("name");
		String remarks = request.getParameter("remarks");
		String id = request.getParameter("id");
		try {
			PrintWriter pw = response.getWriter();
			int i=0;
			if(!"".equals(id)&&id!=null){
				i += this.roleService.updateRole(Integer.parseInt(id),name,remarks);
			}else{
				i += this.roleService.saveRole(name, remarks);
			}
				Map<String,Object> map=new HashMap<>();
				if(i>0){
					map.put("success", true);
				}else{
					map.put("success", false);
					map.put("errorInfo", "系统繁忙，请稍后再试!");
				}
				pw.write(JSONObject.toJSON(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void deleteRole(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		try {
			PrintWriter pw = response.getWriter();
			if(!"".equals(id)&&id!=null){
				int i = this.roleService.deleteRole(Integer.parseInt(id));
				Map<String,Object> map=new HashMap<>();
				if(i>0){
					map.put("success", true);
				}else{
					map.put("success", false);
					map.put("errorInfo", "系统繁忙，请稍后再试!");
				}
				pw.write(JSONObject.toJSON(map).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}