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

import cn.erp.domain.GoodsJson;
import cn.erp.domain.SaleList;
import cn.erp.domain.SaleListGoods;
import cn.erp.service.SaleListGoodsService;
import cn.erp.service.SaleListService;
import cn.erp.service.impl.SaleListGoodsServiceImpl;
import cn.erp.service.impl.SaleListServiceImpl;
import cn.erp.utils.StringUtils;


@WebServlet("/admin/saleList/*")
public class SaleListServlet extends HttpServlet{
	
	private SaleListService saleListService = new SaleListServiceImpl();
	private SaleListGoodsService saleListGoodsService = new SaleListGoodsServiceImpl();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/"));
		if("/save".equals(uri)){
			save(req,resp);
		}
		if("/list".equals(uri)){
			searchSaleList(req,resp);
		}
		if("/listGoods".equals(uri)){
			showListGoods(req,resp);
		}
		if("/delete".equals(uri)){
			delete(req,resp);
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
			String code = goodsJson.split(",")[0].split(":")[1];
			String codeStr = code.substring(1, code.length()-1);
			SaleList saleList = new SaleList();
			saleList.setAmount_paid(Double.parseDouble(amount_paid));
			saleList.setAmount_payable(Double.parseDouble(amount_payable));
			saleList.setRemarks(remarks);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			saleList.setSale_date(sdf.parse(sale_date));
			String dateStr = "";
			String[] date = sale_date.split("-");
			for (int i = 0; i < date.length; i++) {
				dateStr+=date[i];
			}
			saleList.setSale_number("XS"+dateStr+codeStr);
			saleList.setState(Integer.parseInt(state));
			saleList.setUser_id(1);
			saleList.setCustomer_id(Integer.parseInt(customer_id));
			
			
			ArrayList<GoodsJson> list = JSON.parseObject(goodsJson, new TypeReference<ArrayList<GoodsJson>>() {});
			GoodsJson goodsJson2 = list.get(0);
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
			
			int i = saleListService.addSaleList(saleList);
			if(i == 1){
				int id = saleListService.findByMaxId();
				saleListGoods.setSale_list_id(id);
				saleListGoodsService.insertSaleListGoods(saleListGoods);
			}
			Map<String, Object> resultMap = new HashMap<>();
			if(i == 1){
				resultMap.put("success", true);
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
			Integer customer_id=null;
			if(!"".equals(req.getParameter("customer.id"))&&req.getParameter("customer.id")!=null){
				customer_id=Integer.parseInt(req.getParameter("customer.id"));
			}
			Integer state=null;
			if(!"".equals(req.getParameter("state"))&&req.getParameter("state")!=null){
				state = Integer.parseInt(req.getParameter("state"));
			}
			String bSaleDate = req.getParameter("bSaleDate");
			String eSaleDate = req.getParameter("eSaleDate");
			List<SaleList> list = null;
			if(sale_number == null){
				list = saleListService.selectByCondition("",customer_id,state,bSaleDate,eSaleDate);
			}else{
				 list = saleListService.selectByCondition(sale_number,customer_id,state,bSaleDate,eSaleDate);
			}
			String jsonData = JSONObject.toJSON(list).toString();
			jsonData = "{\"rows\":"+jsonData+"}";
			String data = StringUtils.removeUnderlineAndUpperCase(jsonData);
			resp.getWriter().write(data);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showListGoods(HttpServletRequest req, HttpServletResponse resp) {
		try {
			PrintWriter pw = resp.getWriter();
			Integer id=Integer.parseInt(req.getParameter("saleListId"));
			System.out.println(id);
			List<SaleListGoods> list = saleListService.findAllListGoodsById(id);
			Map<String,Object> map=new HashMap<>();
			map.put("rows", list);
			Object json = JSONObject.toJSON(map);
			String str = json.toString();
			str = StringUtils.removeUnderlineAndUpperCase(str);
			pw.write(str);
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
			if(i == 1){
				map.put("success", true);
			}else{
				map.put("errorInfo", "删除失败！");
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
}
