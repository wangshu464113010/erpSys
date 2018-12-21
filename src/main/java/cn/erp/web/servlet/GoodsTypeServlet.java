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

import cn.erp.domain.GoodsType;
import cn.erp.domain.PurchaseList;
import cn.erp.service.GoodstypeService;
import cn.erp.service.PurchaseService;
import cn.erp.service.impl.GoodstypeServiceImpl;
import cn.erp.service.impl.PurchaseServiceImpl;

/**
 * Servlet implementation class Goodes
 */
@WebServlet("/admin/goodsType/*")
public class GoodsTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GoodstypeService goodstypeService = new GoodstypeServiceImpl();
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
		if("/save".equals(uri)){//保存商品类型
			saveGoodsType(request, response);
		}
		if("/delete".equals(uri)){//删除商品类型
			String id = request.getParameter("id");
			try {
				if(goodstypeService.delete(Integer.parseInt(id))>0){
					PrintWriter pw = response.getWriter();
					pw.write("{\"success\":true}");
				}
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
		}
	
	}
	
	private void saveGoodsType(HttpServletRequest request, HttpServletResponse response) throws IOException {
		GoodsType goodstype = new GoodsType();
		String name = request.getParameter("name");
		String parentId = request.getParameter("parentId");
		goodstype.setName(name);
		goodstype.setP_id(Integer.parseInt(parentId));
		try {
			goodstypeService.addGoodsType(goodstype);
			PrintWriter pw = response.getWriter();
			pw.write("{\"success\":true}");
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
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
