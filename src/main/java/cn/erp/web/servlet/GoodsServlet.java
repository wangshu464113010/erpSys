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
import com.mchange.v1.util.StringTokenizerUtils;

import cn.erp.domain.Goods;
import cn.erp.service.GoodsService;
import cn.erp.service.GoodstypeService;
import cn.erp.service.SaleListGoodsService;
import cn.erp.service.impl.GoodsServiceImpl;
import cn.erp.service.impl.GoodstypeServiceImpl;
import cn.erp.service.impl.SaleListGoodsServiceImpl;
import cn.erp.utils.StringUtils;

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
			String name = req.getParameter("name");
			if(name == null || "".equals(name)){
				findAllGoodsAndGoodsType(req,resp);
			}else{
				try {
					PrintWriter pw = resp.getWriter();
					Integer pageNow = Integer.parseInt(req.getParameter("page"));
					Integer pageSize = Integer.parseInt(req.getParameter("rows"));
					List<Goods> list = goodsService.findLikeGoods(name, pageNow, pageSize);
					Object json = JSONObject.toJSON(list);
					String string = StringUtils.removeUnderlineAndUpperCase(json.toString());
					//System.out.println(string);
					//{"total":0,"rows":[]}
					string = "{\"total\":"+goodsService.countLikeGoods(name)+",\"rows\":"+string+"}";
					pw.write(string);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
		if("/delete".equals(uri)){
			String id = req.getParameter("id");
			try {
				if(goodsService.delete(Integer.parseInt(id))>0){
					resp.getWriter().write("{\"success\":true}");
				}
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
		}
		if("/save".equals(uri)){
			Goods goods = injectionSaveAndUpdateGoods(req);
			String id = req.getParameter("id");
			try {
			if(id==null || id == ""){
				if(goodsService.insert(goods)>0){
					resp.getWriter().write("{\"success\":true}");
				}
			}else{
				goods.setId(Integer.parseInt(id));
				if(goodsService.update(goods)>0){
					resp.getWriter().write("{\"success\":true}");
				}
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}



	private Goods injectionSaveAndUpdateGoods(HttpServletRequest req) {
		String typeid = req.getParameter("type.id");
		String code = req.getParameter("code");
		String name = req.getParameter("name");
		String model = req.getParameter("model");
		String unit = req.getParameter("unit");
		String purchasingPrice = req.getParameter("purchasingPrice");
		String sellingPrice = req.getParameter("sellingPrice");
		String minNum = req.getParameter("minNum");
		String producer = req.getParameter("producer");
		String remarks = req.getParameter("remarks");
		Goods goods = new Goods();
		goods.setType_id(Integer.parseInt(typeid));
		goods.setCode(code);
		goods.setName(name);
		goods.setModel(model);
		goods.setUnit(unit);
		goods.setUnit(unit);
		goods.setPurchasing_price(Double.parseDouble(purchasingPrice));
		goods.setSelling_price(Double.parseDouble(sellingPrice));
		goods.setMin_num(Integer.parseInt(minNum));
		goods.setProducer(producer);
		goods.setRemarks(remarks);
		return goods;
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
			System.out.println(list.size());
			String string = JSONObject.toJSON(list).toString();
			string = "{\"total\":"+total+",\"rows\":"+string+"}";
			string = StringUtils.removeUnderlineAndUpperCase(string);
//			string = string.replaceAll("purchasing_price", "purchasingPrice");
//			string = string.replaceAll("selling_price", "sellingPrice");
//			//string = string.replaceAll("last_purchasing_price", "lastPurchasingPrice");
//			string = string.replaceAll("last_purchasingPrice", "lastPurchasingPrice");
//			string = string.replaceAll("inventory_quantity", "inventoryQuantity");
//			string = string.replaceAll("type_id", "typeId");
//			string = string.replaceAll("min_num", "minNum");
//			string = string.replaceAll("p_id", "pId");
			pw.write(string);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
