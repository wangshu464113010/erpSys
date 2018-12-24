package cn.erp.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import cn.erp.domain.CustomerReturnList;
import cn.erp.domain.CustomerReturnListCount;
import cn.erp.domain.CustomerReturnListGoods;
import cn.erp.domain.GoodsJson;
import cn.erp.domain.SaleList;
import cn.erp.domain.SaleListGoods;
import cn.erp.service.CustomerReturnListGoodsService;
import cn.erp.domain.User;
import cn.erp.service.CustomerReturnListService;
import cn.erp.service.impl.CustomerReturnListGoodsServiceImpl;
import cn.erp.service.impl.CustomerReturnListServiceImpl;
import cn.erp.utils.LogUtils;
import cn.erp.utils.StringUtils;

@WebServlet("/admin/customerReturnList/*")
public class CustomerReturnListServlet extends HttpServlet {
	private CustomerReturnListGoodsService customerReturnListGoodsService = new CustomerReturnListGoodsServiceImpl();
	private CustomerReturnListService customerReturnListService = new CustomerReturnListServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/"));
		if ("/list".equals(uri)) {
			findCustomerReturnListAll(req, resp);
		}
		if ("/listGoods".equals(uri)) {
			findCustomerReturnListGoodsAll(req, resp);
		}
		if ("/listCount".equals(uri)) {
			customerReturnListCount(req, resp);
		if("/delete".equals(uri)){
			deleteCustomerReturnListGoods(req, resp);
		}
		if ("/save".equals(uri)) {
			//插入数据
			try {
				insertCustomerRetrunList(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

	// 客户退货单号信息
	
	private void insertCustomerRetrunList(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		// 拿到页面的值
		Integer customer_id = Integer.parseInt(req.getParameter("customer.id"));
		Double amount_payable = Double.parseDouble(req.getParameter("amountPayable"));
		Double amount_paid = Double.parseDouble(req.getParameter("amountPaid"));
		String customer_return_date = req.getParameter("customerReturnDate");
		String remarks = req.getParameter("remarks");
		Integer state = Integer.parseInt(req.getParameter("state"));
		//拼接出订单号
		String[] split = customer_return_date.split("-");
		StringBuffer sb = new StringBuffer();
		sb.append(split[0]);
		for (int i =1;i<split.length;++i) {
			char c = split[i].charAt(0);
			String str = c + "";
			String replaceFirst = split[i];
			sb.append(replaceFirst);
		}
		String goodsJson = req.getParameter("goodsJson");
		String code = goodsJson.split(",")[0].split(":")[1];
		String codeStr = code.substring(1, code.length()-1);
		String customer_return_number = "XT"+sb.toString()+codeStr;
		// 拿到页面的值
		try {
			int i = customerReturnListService.insertCustomerRetrunList(amount_paid, amount_payable, customer_return_date, customer_return_number, remarks, state, customer_id);
			Map<String, Object> map = new HashMap<>();
			if(i == 0){
				map.put("errorInfo", "删除失败！");
			}else{
				map.put("success", true);
			}
			resp.getWriter().write(JSONObject.toJSON(map).toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//拿到具体商品
		ArrayList<GoodsJson> list = JSON.parseObject(goodsJson, new TypeReference<ArrayList<GoodsJson>>() {});
		CustomerReturnListGoods customerReturnListGoods = new CustomerReturnListGoods();
		for (int index = 0; index < list.size(); index++) {
			GoodsJson goodsJson2 = list.get(index);
			customerReturnListGoods.setCode(goodsJson2.getCode());
			customerReturnListGoods.setModel(goodsJson2.getModel());
			customerReturnListGoods.setName(goodsJson2.getName());
			customerReturnListGoods.setNum(Integer.parseInt(goodsJson2.getNum()));
			customerReturnListGoods.setPrice(Double.parseDouble(goodsJson2.getPrice()));
			customerReturnListGoods.setTotal((double)goodsJson2.getTotal());
			customerReturnListGoods.setUnit(goodsJson2.getUnit());
			customerReturnListGoods.setType_id(goodsJson2.getTypeId());
			customerReturnListGoods.setGoods_id(goodsJson2.getGoodsId());
			int i = customerReturnListGoodsService.insertCustomerRetrunListGoods(customerReturnListGoods);
		}
	}
	
	
	//客户退货单号信息
	private void findCustomerReturnListAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter pw = resp.getWriter();
		// 拿到页面的值
		String customer_return_number = req.getParameter("customerReturnNumber");
		Integer customer_id = null;
		if (!"".equals(req.getParameter("customer.id")) && req.getParameter("customer.id") != null) {
			customer_id = Integer.parseInt(req.getParameter("customer.id"));
		}
		Integer state = null;
		if (!"".equals(req.getParameter("state")) && req.getParameter("state") != null) {
			state = Integer.parseInt(req.getParameter("state"));
		}
		String bCustomerReturnDate = req.getParameter("bCustomerReturnDate");
		String eCustomerReturnDate = req.getParameter("eCustomerReturnDate");
		try {
			List<CustomerReturnList> list = null;
			list = customerReturnListService.findCustomerReturnListAll(customer_return_number, customer_id, state,
					bCustomerReturnDate, eCustomerReturnDate);
			String jsonData = JSONObject.toJSON(list).toString();
			jsonData = "{\"rows\":" + jsonData + "}";
			jsonData = "{\"rows\":"+jsonData+"}";
			String string = StringUtils.removeUnderlineAndUpperCase(jsonData);
			pw.write(string);
			User u = (User) req.getSession().getAttribute("user");
			LogUtils.insertLog("查询操作", "查询客户退货单号信息",u.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//客户具体信息单号信息
	private void findCustomerReturnListGoodsAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter pw = resp.getWriter();
		// 拿到页面的值
		Integer customer_return_list_id  = Integer.parseInt(req.getParameter("customerReturnListId"));
		try {
			List<CustomerReturnListGoods> list = null;
			list = customerReturnListService.findCustomerReturnListGoodsAll(customer_return_list_id);
			String jsonData = JSONObject.toJSON(list).toString();
			jsonData = "{\"rows\":"+jsonData+"}";
			String string = StringUtils.removeUnderlineAndUpperCase(jsonData);
			pw.write(string);
			User u = (User) req.getSession().getAttribute("user");
			LogUtils.insertLog("查询操作", "查询客户具体信息单号信息",u.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	private void customerReturnListCount(HttpServletRequest req, HttpServletResponse resp) throws IOException {

	}
	//删除
	private void deleteCustomerReturnListGoods(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			String customer_return_list_id = req.getParameter("id");
			int i = customerReturnListService.deleteById(Integer.parseInt(customer_return_list_id));
			Map<String, Object> map = new HashMap<>();
			if(i == 0){
				map.put("errorInfo", "删除失败！");
			}else{
				map.put("success", true);
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
