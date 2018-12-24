package cn.erp.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.alibaba.fastjson.TypeReference;

import cn.erp.domain.CountSale;
import cn.erp.domain.GoodsJson;
import cn.erp.domain.SaleList;
import cn.erp.domain.SaleListCount;
import cn.erp.domain.SaleListGoods;
import cn.erp.domain.User;
import cn.erp.service.SaleListGoodsService;
import cn.erp.service.SaleListService;
import cn.erp.service.impl.SaleListGoodsServiceImpl;
import cn.erp.service.impl.SaleListServiceImpl;
import cn.erp.utils.LogUtils;
import cn.erp.utils.StringUtils;

@WebServlet("/admin/saleList/*")
public class SaleListServlet extends HttpServlet {

	private SaleListService saleListService = new SaleListServiceImpl();
	private SaleListGoodsService saleListGoodsService = new SaleListGoodsServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/"));
		if("/save".equals(uri)){
			//保存客户销售清单
			save(req,resp);
		}
		if("/list".equals(uri)){
			//查询客户
			searchSaleList(req,resp);
		}
		if("/listGoods".equals(uri)){
			//查询客户销售清单商品
			showListGoods(req,resp);
		}
		if("/delete".equals(uri)){
			//删除客户销售清单
			delete(req,resp);
		}
		if("/countSaleByDay".equals(uri)){///saleList/countSaleByDay
			//begin	2018-12-15
			//end	2018-12-2
		}
		//按月报表信息
		if("/countSaleByMonth".equals(uri)){
			countSaleByMonth(req,resp);
		}
		if ("/update".equals(uri)) {
			updateState(req, resp);
		}
	    if ("/listCount".equals(uri)) {
	    	saleListCount(req, resp);
	    }

	}

	public void save(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String customer_id = req.getParameter("customer.id");
			String amount_payable = req.getParameter("amountPayable");
			String amount_paid = req.getParameter("amountPaid");
			String sale_date = req.getParameter("saleDate");
			String remarks = req.getParameter("remarks");
			String state = req.getParameter("state");
			String sale_number = req.getParameter("saleNumber");
			String goodsJson = req.getParameter("goodsJson");
			SaleList saleList = new SaleList();
			saleList.setAmount_paid(Double.parseDouble(amount_paid));
			saleList.setAmount_payable(Double.parseDouble(amount_payable));
			saleList.setRemarks(remarks);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			saleList.setSale_date(sdf.parse(sale_date));
			String dateStr = "";
			String[] date = sale_date.split("-");
			for (int i = 0; i < date.length; i++) {
				dateStr += date[i];
			}
			String saleNumber = saleListService.findSaleNumber();
			String saleNumberStr = (Integer.parseInt(saleNumber.substring(10, saleNumber.length()))+1)+"";
			int index = saleNumberStr.length();
			for (int i = 0; i < saleNumberStr.length(); i++) {
				if(index < 4){
					saleNumberStr = "0" + saleNumberStr;
					index++;
				}
			}
			saleList.setSale_number("XS" + dateStr + saleNumberStr);
			saleList.setState(Integer.parseInt(state));
			saleList.setUser_id(1);
			saleList.setCustomer_id(Integer.parseInt(customer_id));

			ArrayList<GoodsJson> list = JSON.parseObject(goodsJson, new TypeReference<ArrayList<GoodsJson>>() {});
			//插入数据
			int i = saleListService.addSaleList(saleList);
			for (int getindex = 0; getindex < list.size(); getindex++) {
				GoodsJson goodsJson2 = list.get(getindex);
				SaleListGoods saleListGoods = new SaleListGoods();
				saleListGoods.setCode(goodsJson2.getCode());
				saleListGoods.setModel(goodsJson2.getModel());
				saleListGoods.setName(goodsJson2.getName());
				saleListGoods.setNum(Integer.parseInt(goodsJson2.getNum()));
				saleListGoods.setPrice(Float.parseFloat(goodsJson2.getPrice()));
				saleListGoods.setTotal(goodsJson2.getTotal());
				saleListGoods.setUnit(goodsJson2.getUnit());
				saleListGoods.setType_id(goodsJson2.getTypeId());
				saleListGoods.setGoods_id(goodsJson2.getGoodsId());
				if (i == 1) {
					int id = saleListService.findByMaxId();
					saleListGoods.setSale_list_id(id);
					saleListGoodsService.insertSaleListGoods(saleListGoods);
				}
			}
			Map<String, Object> resultMap = new HashMap<>();
			if (i == 1) {
				resultMap.put("success", true);
				User u = (User) req.getSession().getAttribute("user");
				LogUtils.insertLog("添加操作", "保存客户销售清单",u.getId());
			}else{
				resultMap.put("errorInfo", "保存失败！");
			}
			resp.getWriter().write(JSONObject.toJSON(resultMap).toString());


		} catch (ParseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void searchSaleList(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String sale_number = req.getParameter("saleNumber");
			Integer customer_id = null;
			if (!"".equals(req.getParameter("customer.id")) && req.getParameter("customer.id") != null) {
				customer_id = Integer.parseInt(req.getParameter("customer.id"));
			}
			Integer state = null;
			if (!"".equals(req.getParameter("state")) && req.getParameter("state") != null) {
				state = Integer.parseInt(req.getParameter("state"));
			}
			String bSaleDate = req.getParameter("bSaleDate");
			String eSaleDate = req.getParameter("eSaleDate");
			List<SaleList> list = null;
			if (sale_number == null) {
				list = saleListService.selectByCondition("", customer_id, state, bSaleDate, eSaleDate);
			} else {
				list = saleListService.selectByCondition(sale_number, customer_id, state, bSaleDate, eSaleDate);
			}
			String jsonData = JSONObject.toJSON(list).toString();
			jsonData = "{\"rows\":" + jsonData + "}";
			String data = StringUtils.removeUnderlineAndUpperCase(jsonData);
			resp.getWriter().write(data);
			User u = (User) req.getSession().getAttribute("user");
			LogUtils.insertLog("查询操作", "查询客户信息",u.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showListGoods(HttpServletRequest req, HttpServletResponse resp) {
		try {
			PrintWriter pw = resp.getWriter();
			Integer id = Integer.parseInt(req.getParameter("saleListId"));
			List<SaleListGoods> list = saleListService.findAllListGoodsById(id);
			Map<String, Object> map = new HashMap<>();
			map.put("rows", list);
			Object json = JSONObject.toJSON(map);
			String str = json.toString();
			str = StringUtils.removeUnderlineAndUpperCase(str);
			pw.write(str);
			User u = (User) req.getSession().getAttribute("user");
			LogUtils.insertLog("查询操作", "查询客户详细信息",u.getId());
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String id = req.getParameter("id");
			int i = saleListService.deleteById(Integer.parseInt(id));
			Map<String, Object> map = new HashMap<>();
			if (i == 1) {
				map.put("success", true);
			} else {
				map.put("errorInfo", "删除失败！");
				User u = (User) req.getSession().getAttribute("user");
				LogUtils.insertLog("删除操作", "删除客户信息",u.getId());
			}
			resp.getWriter().write(JSONObject.toJSON(map).toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void countSaleByMonth(HttpServletRequest req, HttpServletResponse resp) {
		String begin = req.getParameter("begin");
		String end = req.getParameter("end");
		try {
			PrintWriter pw = resp.getWriter();
			List<CountSale> list = this.saleListService.findByMonthTj(begin, end);
			Map<String,Object> map=new HashMap<>();
			map.put("success", true);
			map.put("rows", list);
			pw.write(JSONObject.toJSON(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateState(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			String id = req.getParameter("id");
			int i = saleListService.updataState(Integer.parseInt(id));
			Map<String, Object> map = new HashMap<>();
			if (i == 0) {
				map.put("errorInfo", "操作超时！");
			} else {
				map.put("success", true);
			}
			resp.getWriter().write(JSONObject.toJSON(map).toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void saleListCount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			String bSaleDate = req.getParameter("bSaleDate");
			String eSaleDate = req.getParameter("eSaleDate");
			Integer type_id = null;
			if (!"".equals(req.getParameter("type.id")) && req.getParameter("type.id") != null) {
				type_id = Integer.parseInt(req.getParameter("type.id"));
			}
			String codeOrName = req.getParameter("codeOrName");
			List<SaleListCount> list = saleListService.findListCount(bSaleDate, eSaleDate, type_id, codeOrName);
			String jsonData = JSONObject.toJSON(list).toString();
			jsonData = "{\"rows\":" + jsonData + "}";
			String string = StringUtils.removeUnderlineAndUpperCase(jsonData);
			resp.getWriter().write(string);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
