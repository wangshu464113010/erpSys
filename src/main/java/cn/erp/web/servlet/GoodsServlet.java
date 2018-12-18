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

import cn.erp.domain.Goods;
import cn.erp.domain.GoodsData;
import cn.erp.domain.GoodsType;
import cn.erp.service.GoodsService;
import cn.erp.service.GoodstypeService;
import cn.erp.service.impl.GoodsServiceImpl;
import cn.erp.service.impl.GoodstypeServiceImpl;

@WebServlet("/admin/goods/*")
public class GoodsServlet extends HttpServlet{
	
	private GoodsService goodsService = new GoodsServiceImpl();
	private GoodstypeService goodstypeService = new GoodstypeServiceImpl();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/"));
		if("/listInventory".equals(uri)){
			findAll(req,resp);
		}
		if("/loadTreeInfo".equals(uri)){
			showTree(req,resp);
		}
		if("/list".equals(uri)){
			findAllGoodsAndGoodsType(resp);
		}
	}
	
	
	public void findAll(HttpServletRequest req, HttpServletResponse resp){
		try {
			GoodsData goodsData = new GoodsData();
			List<Goods> data = goodsService.findAll();
			for (Goods goods : data) {
				GoodsType goodstype = goodstypeService.findByPid(goods.getType_id());
				goods.setType(goodstype);
			}
			goodsData.setTotal(data.size());
			goodsData.setRows(data);
			resp.getWriter().write(JSONObject.toJSON(goodsData).toString().
					replaceAll("inventory_quantity", "inventoryQuantity").
					replaceAll("min_num", "minNum").
					replaceAll("purchasing_price", "purchasingPrice").
					replaceAll("selling_price", "sellingPrice").
					replaceAll("p_id", "pId").
					replaceAll("last_purchasing_price", "lastPurchasingPrice"));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showTree(HttpServletRequest req, HttpServletResponse resp){
		
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
			pw.write(string);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
