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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.erp.domain.MenuVO;
import cn.erp.domain.Page;
import cn.erp.domain.Role;
import cn.erp.domain.User;
import cn.erp.domain.User_Role;
import cn.erp.service.MenuVOService;
import cn.erp.service.RoleService;
import cn.erp.service.impl.MenuVOServiceImpl;
import cn.erp.service.impl.RoleServiceImpl;
import cn.erp.utils.LogUtils;
import cn.erp.utils.StringUtils;


@WebServlet("/admin/role/*")
public class RoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private RoleService roleService=new RoleServiceImpl();
	private MenuVOService menuVOService= new MenuVOServiceImpl();
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/"));
		if("/list".equals(uri)){
			  String name= request.getParameter("name");
			   if(name==null||name.equals("")) {
				   findAllRole(request,response);
			   }else {
				   findlikerole(request,response);
			   }
		}
		if("/save".equals(uri)){
			saleRole(request,response);
		}
		if("/delete".equals(uri)){
			deleteRole(request,response);
		}		
		if("/loadCheckMenuInfo".equals(uri)){
			int parentId= Integer.parseInt(request.getParameter("parentId"));
			int roleId= Integer.parseInt(request.getParameter("roleId"));
			try {
				List<MenuVO> list = menuVOService.findAll(parentId, roleId);
				Object json = JSONObject.toJSON(list);
				response.getWriter().write(json.toString().replaceAll("icon", "iconCls"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}if("/listAll".equals(uri)) {
			findrolefit(request,response);
		}
	}
	
	private void findrolefit(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Role> list = roleService.findrolefit();
			User u = (User) request.getSession().getAttribute("user");
			LogUtils.insertLog("修改操作", "修改角色权限信息",u.getId());
			String s = "{\"rows\":"+JSONObject.toJSON(list).toString()+"}";
			try {
				response.getWriter().write(s);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void findlikerole(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String name = request.getParameter("name");
		Page<Role>page1=new Page<Role>();
		page1.setSize(Integer.parseInt(rows));			
	    page1.setPageNow(Integer.parseInt(page));
		try {
			List<Role>list = roleService.findlikerole(page1, name);
			String string = JSONObject.toJSON(list).toString();
			int total = roleService.count();
			string = "{\"total\":"+total+",\"rows\":"+string+"}";				
			response.getWriter().write(StringUtils.removeUnderlineAndUpperCase(string));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			User u = (User) request.getSession().getAttribute("user");
			LogUtils.insertLog("查询操作", "查询所有角色信息",u.getId());
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
					User u = (User) request.getSession().getAttribute("user");
					LogUtils.insertLog("查询操作", "查询销售角色",u.getId());
				}else{
					map.put("success", false);
					map.put("errorInfo", "ϵͳ��æ�����Ժ�����!");
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
					User u = (User) request.getSession().getAttribute("user");
					LogUtils.insertLog("删除操作", "删除角色信息",u.getId());
				}else{
					map.put("success", false);
					map.put("errorInfo", "ϵͳ��æ�����Ժ�����!");
				}
				pw.write(JSONObject.toJSON(map).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}