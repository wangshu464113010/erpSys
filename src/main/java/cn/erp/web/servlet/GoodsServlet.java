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
import cn.erp.domain.GoodsGoosTypeVO;
import cn.erp.domain.Page;
import cn.erp.domain.User;
import cn.erp.service.GoodsGoosTypeVOService;
import cn.erp.service.GoodsGoosTypeVOStageService;
import cn.erp.service.GoodsService;
import cn.erp.service.GoodstypeService;
import cn.erp.service.SaleListGoodsService;
import cn.erp.service.impl.GoodsGoosTypeVOStageServiceImpl;
import cn.erp.service.ListAlarmService;
import cn.erp.service.impl.GoodsServiceImpl;
import cn.erp.service.impl.GoodstypeServiceImpl;
import cn.erp.service.impl.SaleListGoodsServiceImpl;
import cn.erp.service.impl.ListAlarmServiceImpl;
import cn.erp.utils.LogUtils;
import cn.erp.utils.StringUtils;

@WebServlet("/admin/goods/*")
public class GoodsServlet extends HttpServlet {

	private GoodsService goodsService = new GoodsServiceImpl();
	private GoodstypeService goodstypeService = new GoodstypeServiceImpl();
	private SaleListGoodsService saleListGoodsService = new SaleListGoodsServiceImpl();
	private GoodsGoosTypeVOStageService goodsGoosTypeVOStageService = new GoodsGoosTypeVOStageServiceImpl();

	private ListAlarmService listAlarmService = new ListAlarmServiceImpl();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/"));
		if ("/listHasInventoryQuantity".equals(uri)) {
			findlastall(req, resp);
		}
		if ("/saveStore".equals(uri)) {		
			try {
				insertsaveStore(req, resp);
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("/deleteStock".equals(uri)) {
			try {
				deletesaveStore(req,resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if ("/listInventory".equals(uri)) {
			findAll(req, resp);
		}
		if ("/loadTreeInfo".equals(uri)) {
			showTree(req, resp);
		}
		if ("/list".equals(uri)) {
			String name = req.getParameter("name");
			if (name == null || "".equals(name)) {
				findAllGoodsAndGoodsType(req, resp);
			} else {
				try {
					PrintWriter pw = resp.getWriter();
					Integer pageNow = Integer.parseInt(req.getParameter("page"));
					Integer pageSize = Integer.parseInt(req.getParameter("rows"));
					List<Goods> list = goodsService.findLikeGoods(name, pageNow, pageSize);
					Object json = JSONObject.toJSON(list);
					String string = StringUtils.removeUnderlineAndUpperCase(json.toString());
					string = "{\"total\":" + goodsService.countLikeGoods(name) + ",\"rows\":" + string + "}";
					pw.write(string);

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		if ("/genGoodsCode".equals(uri)) {//获取商品的code最大值，无需进行日志记录
			try {
				String maxGoodsCode = goodsService.getMaxGoodsCode();
				PrintWriter pw = resp.getWriter();
				pw.write(maxGoodsCode);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//删除商品
		if ("/delete".equals(uri)) {
			String id = req.getParameter("id");
			try {
				if (goodsService.delete(Integer.parseInt(id)) > 0) {
					resp.getWriter().write("{\"success\":true}");
				}
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
		}
		
		if ("/save".equals(uri)) {
			Goods goods = injectionSaveAndUpdateGoods(req);
			String id = req.getParameter("id");
			try {
				if (id == null || id == "") {
					if (goodsService.insert(goods) > 0) {//保存商品
						resp.getWriter().write("{\"success\":true}");
						User u = (User) req.getSession().getAttribute("user");
						LogUtils.insertLog("添加操作", "添加一条商品信息",u.getId());
					}
				} else {
					goods.setId(Integer.parseInt(id));
					if (goodsService.update(goods) > 0) {//更新商品信息
						resp.getWriter().write("{\"success\":true}");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if("/listAlarm".equals(uri)){
			List<Goods> list=null;
			
			try {
				list=listAlarmService.findAll();
//				String string = JSONObject.toJSON(list).toString();
//				string = "{\"rows\":"+string+"}";
//				StringUtils.removeUnderlineAndUpperCase(string);
//				System.out.println(string);
//				resp.getWriter().write(string);
				String string = JSONObject.toJSON(list).toString();
				string = "{\"rows\":"+string+"}";
				string = string.replaceAll("purchasing_price", "purchasingPrice");
				string = string.replaceAll("selling_price", "sellingPrice");
				//string = string.replaceAll("last_purchasing_price", "lastPurchasingPrice");
				string = string.replaceAll("last_purchasingPrice", "lastPurchasingPrice");
				string = string.replaceAll("inventory_quantity", "inventoryQuantity");
				string = string.replaceAll("type_id", "typeId");
				string = string.replaceAll("min_num", "minNum");
				string = string.replaceAll("p_id", "pId");
//				System.out.println(string);
				resp.getWriter().write(string);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void deletesaveStore(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		// TODO Auto-generated method stub
		String id = req.getParameter("id");
		GoodsGoosTypeVO goodsGoosTypeVO = new GoodsGoosTypeVO();
		goodsGoosTypeVO.setId(Integer.parseInt(id));
		try {
			goodsGoosTypeVOStageService.deletesaveStore(Integer.parseInt(id));
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		resp.getWriter().write("{\"success\":true}");
		User u = (User) req.getSession().getAttribute("user");
		LogUtils.insertLog("删除操作", "删除商品种类信息",u.getId());
	}

	private void insertsaveStore(HttpServletRequest req, HttpServletResponse resp) throws NumberFormatException, SQLException, IOException {
		String id = req.getParameter("id");
		String num = req.getParameter("num");
		String price = req.getParameter("price");
		GoodsGoosTypeVO goodsGoosTypeVO = new GoodsGoosTypeVO();
		goodsGoosTypeVO.setId(Integer.parseInt(id));
		goodsGoosTypeVO.setMinNum(Integer.parseInt(num));
		goodsGoosTypeVO.setSellingPrice(Double.parseDouble(price));
		goodsGoosTypeVOStageService.updatesaveStoreThree(Integer.parseInt(id), Integer.parseInt(num), Double.parseDouble(price));
		resp.getWriter().write("{\"success\":true}");
		User u = (User) req.getSession().getAttribute("user");
		LogUtils.insertLog("添加操作", "添加一条商品信息",u.getId());
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

	public void findAll(HttpServletRequest req, HttpServletResponse resp) {
		try {
			findAllGoodsAndGoodsType(req, resp);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void showTree(HttpServletRequest req, HttpServletResponse resp) {

	}

	private void findAllGoodsAndGoodsType(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter pw = resp.getWriter();
		Integer page = Integer.parseInt(req.getParameter("page"));
		Integer rows = Integer.parseInt(req.getParameter("rows"));
		String codeOrName = req.getParameter("codeOrName");
		String typeid = req.getParameter("type.id");
		List<Goods> list = null;
		try {
			if (!"".equals(typeid) && typeid != null) {
				list = goodsService.findAll(page, rows, Integer.parseInt(typeid), codeOrName);
			} else {
				list = goodsService.findAll(page, rows, null, codeOrName);
			}
			int total = goodsService.count();
			System.out.println(list.size());
			String string = JSONObject.toJSON(list).toString();
			string = "{\"total\":" + total + ",\"rows\":" + string + "}";
			string = StringUtils.removeUnderlineAndUpperCase(string);
			pw.write(string);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void saveStore(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String num = request.getParameter("num");
		String price = request.getParameter("price");
		Double Stockamount = Double.parseDouble(num) * Double.parseDouble(price);
		System.out.println(id);
				
	}

//查出期初库存的所有
	private void findlastall(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		Page<GoodsGoosTypeVO> page1 = new Page<GoodsGoosTypeVO>();
		page1.setSize(Integer.parseInt(rows));
		page1.setPageNow(Integer.parseInt(page));
		try {
			// data来源于service--->dao----->database
			List<GoodsGoosTypeVO> list = goodsGoosTypeVOStageService.findAll(page1);// 把请求响应
			String string = JSONObject.toJSON(list).toString();
			int total = goodsGoosTypeVOStageService.count();
			string = "{\"total\":" + total + ",\"rows\":" + string + "}";
			response.getWriter().write(string);
			User u = (User) request.getSession().getAttribute("user");
			LogUtils.insertLog("查询操作", "查询所有期初库存",u.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
