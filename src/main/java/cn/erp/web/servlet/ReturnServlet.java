package cn.erp.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import cn.erp.domain.Return_List_Goods;
import cn.erp.domain.Return_List;
import cn.erp.domain.GoodsJson;
import cn.erp.domain.ReturnList;
import cn.erp.service.ReturnService;
import cn.erp.service.impl.ReturnServiceImpl;
import utils.StringUtils;
@WebServlet(value="/admin/returnList/*")
public class ReturnServlet  extends HttpServlet {

	private ReturnService returnservice = new ReturnServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/"));
		
		if("/save".equals(uri)){
			saveSupplier(req,resp);
		}
		if("/list".equals(uri)){
			findAllReturnSearch(req,resp);
		}
		if("/listGoods".equals(uri)){
			findAllListGoods(req,resp);
		}
		if("/delete".equals(uri)){
			deleteReturnList(req,resp);
		
	}
}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req,resp);
	}

	private void findAllByType(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		PrintWriter pw = response.getWriter();
		try {
			List<ReturnList> list = returnservice.ReturnList();
			Object json = JSONObject.toJSON(list);
			pw.write(json.toString().replaceAll("name", "text"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void deleteReturnList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter pw = resp.getWriter();
		Integer id = Integer.parseInt(req.getParameter("id"));
		try {
			int index = this.returnservice.deleteReturnList(id);
			Map<String,Object> map=new HashMap<>();
			Object json1=null;
			if(index>0){
				map.put("success", true);
				json1 = JSONObject.toJSON(map);
			}else{
				map.put("success", false);
				map.put("errorInfo", "系统繁忙，请稍后尝试");
				json1 = JSONObject.toJSON(map);
			}
			pw.write(json1.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void findAllListGoods(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter pw = resp.getWriter();
		Integer id=Integer.parseInt(req.getParameter("returnListId"));
		try {
			List<Return_List_Goods> list = this.returnservice.findAllListGoodsById(id);
			Map<String,Object> map=new HashMap<>();
			map.put("rows", list);
			Object json = JSONObject.toJSON(map);
			String str = json.toString();
			str = StringUtils.removeUnderlineAndUpperCase(str);
			pw.write(str);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	private void findAllReturnSearch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter pw = resp.getWriter();
		String returnNumber = req.getParameter("returnNumber");
		Integer supplier_id=null;
		if(!"".equals(req.getParameter("supplier.id"))&&req.getParameter("supplier.id")!=null){
			supplier_id=Integer.parseInt(req.getParameter("supplier.id"));
		}
		Integer state=null;
		if(!"".equals(req.getParameter("state"))&&req.getParameter("state")!=null){
			state = Integer.parseInt(req.getParameter("state"));
		}
		String bPurchaseDate = req.getParameter("bReturnDate");
		String ePurchaseDate = req.getParameter("eReturnDate");
		try {
			List<Return_List> list = null;
			if(returnNumber == null){
				list = this.returnservice.findAllReturnSearch("",supplier_id,state,bPurchaseDate,ePurchaseDate);
			}else{
				list = this.returnservice.findAllReturnSearch(returnNumber,supplier_id,state,bPurchaseDate,ePurchaseDate);
			}
			Map<String,Object> map=new HashMap<>();
			map.put("rows", list);
			Object json = JSONObject.toJSON(map);
			String str = json.toString();
			str = StringUtils.removeUnderlineAndUpperCase(str);
			pw.write(str);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	


	private void saveSupplier(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter pw = resp.getWriter();
		Integer user_id=1;
		String supplier_id = req.getParameter("supplier.id");
		float amount_payable = Float.parseFloat(req.getParameter("amountPayable"));
		float amount_paid = Float.parseFloat(req.getParameter("amountPaid"));
		String returnDate = req.getParameter("returnDate");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date return_date=null;
		try {
			return_date = sdf.parse(returnDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String remarks = req.getParameter("remarks");
		int state = Integer.parseInt(req.getParameter("state"));
		String json = req.getParameter("goodsJson");
		ArrayList<GoodsJson> list = JSON.parseObject(json, new TypeReference<ArrayList<GoodsJson>>() {});
		GoodsJson goodsJson = list.get(0);
		String[] split = returnDate.split("-");
		String pd="";
		for (String string : split) {
			pd+=string;
		}
		String purchase_Number="TH"+pd+goodsJson.getCode();
		
		try {
			int i = this.returnservice.returnJh(user_id, supplier_id, amount_payable, amount_paid, return_date, remarks, state, goodsJson, purchase_Number);
			Map<String,Object> map=new HashMap<>();
			Object json1=null;
			if(i==2){
				map.put("success", true);
				json1 = JSONObject.toJSON(map);
			}else{
				map.put("success", false);
				map.put("errorInfo", "系统繁忙，请稍后尝试");
				json1 = JSONObject.toJSON(map);
			}
			pw.write(json1.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}