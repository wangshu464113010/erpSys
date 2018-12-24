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

import cn.erp.domain.Goodsunit;
import cn.erp.domain.User;
import cn.erp.service.GoodsunitService;
import cn.erp.service.impl.GoodsunitServiceImpl;
import cn.erp.utils.LogUtils;

@WebServlet("/admin/goodsUnit/*")
public class GoodsUnitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//admin/goodsUnit/comboList

	private GoodsunitService goodsunitService = new GoodsunitServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/"));
		if("/listAll".equals(uri)){
			try {
				List<Goodsunit> list = goodsunitService.queryAll();
				Object json = JSONObject.toJSON(list);
				PrintWriter pw = response.getWriter();
				pw.write(json.toString());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if("/comboList".equals(uri)){
			try {
				List<Goodsunit> list = goodsunitService.queryAll();
				Object json = JSONObject.toJSON(list);
				PrintWriter pw = response.getWriter();
				pw.write(json.toString());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if("/save".equals(uri)){
			try {
				String name = request.getParameter("name");
				System.out.println(name);
				Goodsunit goodsunit = new Goodsunit();
				goodsunit.setName(name);
				int i  = goodsunitService.add(goodsunit);
				PrintWriter pw = response.getWriter();
				pw.write("{\"success\":true}");
				User u = (User) request.getSession().getAttribute("user");
				LogUtils.insertLog("添加操作", "添加一个单位",u.getId());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if("/delete".equals(uri)){
			try {
				String id = request.getParameter("id");
				//System.out.println(id);
				Goodsunit goodsunit = new Goodsunit();
				goodsunit.setId(Integer.parseInt(id));
				int i  = goodsunitService.delete(goodsunit);
				PrintWriter pw = response.getWriter();
				pw.write("{\"success\":true}");
				User u = (User) request.getSession().getAttribute("user");
				LogUtils.insertLog("删除操作", "删除一个单位",u.getId());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
