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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.erp.domain.Goods;
import cn.erp.domain.PurchaseList;
import cn.erp.service.GoodsService;
import cn.erp.service.impl.GoodsServiceImpl;

/**
 * Servlet implementation class GoodsServlet
 */
//url /admin/supplier/comboList
@WebServlet("/admin/goods/*" )
public class GoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private PurchaseService purchaseService = new PurchaseServiceImpl();
	private GoodsService goodsService = new GoodsServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uri = req.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/"));
		if("/list".equals(uri)){
			findAllGoodsAndGoodsType(resp);
		}
		if("/genGoodsCode".equals(uri)){
			
		}
		
		
//		
	}
	private void findAllGoodsAndGoodsType(HttpServletResponse resp) throws IOException {
		PrintWriter pw = resp.getWriter();
		try {
			List<Goods> list = goodsService.findAll();
			int total = goodsService.count();
			//System.out.println();
			String string = JSONObject.toJSON(list).toString();
			string = "{\"total\":"+total+",\"rows\":"+string+"}";
		
			string = string.replaceAll("purchasing_price", "purchasingPrice");
			string = string.replaceAll("selling_price", "sellingPrice");
			//string = string.replaceAll("last_purchasing_price", "lastPurchasingPrice");
			string = string.replaceAll("last_purchasingPrice", "lastPurchasingPrice");
			string = string.replaceAll("inventory_quantity", "inventoryQuantity");
			string = string.replaceAll("type_id", "typeId");
			string = string.replaceAll("min_num", "minNum");
			string = string.replaceAll("p_id", "pId");
		//	System.out.println(string);
			
			pw.write(string);
		//	System.out.println(list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
       
//	private void findAllByType(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		PrintWriter pw = response.getWriter();
//		try {
//			List<PurchaseList> list = purchaseService.purchaseList();
//			Object json = JSONObject.toJSON(list);
//			pw.write(json.toString().replaceAll("name", "text"));
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

}
