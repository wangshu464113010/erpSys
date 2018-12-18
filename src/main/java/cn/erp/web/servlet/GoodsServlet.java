package cn.erp.web.servlet;

import java.io.IOException;
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
import cn.erp.domain.Goodstype;
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
	}
	
	
	public void findAll(HttpServletRequest req, HttpServletResponse resp){
		try {
			GoodsData goodsData = new GoodsData();
			List<Goods> data = goodsService.findAll();
			for (Goods goods : data) {
				Goodstype goodstype = goodstypeService.findByPid(goods.getType_id());
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
	
}
