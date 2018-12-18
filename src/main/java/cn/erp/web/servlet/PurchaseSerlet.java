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
import cn.erp.domain.Purchase;
import cn.erp.domain.PurchaseList;
import cn.erp.service.MenuService;
import cn.erp.service.PurchaseService;
import cn.erp.service.impl.MenuServiceImpl;
import cn.erp.service.impl.PurchaseServiceImpl;

@WebServlet("/admin/goodsType/*")
public class PurchaseSerlet extends HttpServlet{
	
	private PurchaseService purchaseService = new PurchaseServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/"));
		if("/loadTreeInfo".equals(uri)){
			findAll(request,response);//
		}
		if("/list".equals(uri)){
			findAllByType(request,response);//
		}
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		try {
			List<PurchaseList> list = purchaseService.purchaseList();
			Object json = JSONObject.toJSON(list);
			pw.write(json.toString().replaceAll("name", "text"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void findAllByType(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		try {
			List<PurchaseList> list = purchaseService.purchaseList();
			Object json = JSONObject.toJSON(list);
			pw.write(json.toString().replaceAll("name", "text"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
