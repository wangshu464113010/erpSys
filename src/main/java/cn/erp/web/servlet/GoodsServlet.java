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

import org.omg.CORBA.Request;

import com.alibaba.fastjson.JSONObject;

import cn.erp.domain.Goods;
import cn.erp.domain.SaleListGoods;
import cn.erp.service.GoodsService;
import cn.erp.service.GoodstypeService;
import cn.erp.service.SaleListGoodsService;
import cn.erp.service.impl.GoodsServiceImpl;
import cn.erp.service.impl.GoodstypeServiceImpl;
import cn.erp.service.impl.SaleListGoodsServiceImpl;

@WebServlet("/admin/goods/*")
public class GoodsServlet extends HttpServlet{
	
	private GoodsService goodsService = new GoodsServiceImpl();
	private GoodstypeService goodstypeService = new GoodstypeServiceImpl();
	private SaleListGoodsService saleListGoodsService = new SaleListGoodsServiceImpl();
	
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
			findAllGoodsAndGoodsType(req,resp);
		}
		if("/genGoodsCode".equals(uri)){
			try {
				String maxGoodsCode = goodsService.getMaxGoodsCode();
				PrintWriter pw = resp.getWriter();
				pw.write(maxGoodsCode);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	
	public void findAll(HttpServletRequest req, HttpServletResponse resp){
		try {
			findAllGoodsAndGoodsType(req,resp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void showTree(HttpServletRequest req, HttpServletResponse resp){
		
	}
	

	private void findAllGoodsAndGoodsType(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		PrintWriter pw = resp.getWriter();
		Integer page = Integer.parseInt(req.getParameter("page"));
		Integer rows = Integer.parseInt(req.getParameter("rows"));
		String codeOrName = req.getParameter("codeOrName");
		String typeid = req.getParameter("type.id");
		List<Goods> list =null;
		try {
			if(!"".equals(typeid)&&typeid!=null){
				list = goodsService.findAll(page,rows,Integer.parseInt(typeid),codeOrName);
			}else{
				list = goodsService.findAll(page,rows,null,codeOrName);
			}
			int total = goodsService.count();
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
