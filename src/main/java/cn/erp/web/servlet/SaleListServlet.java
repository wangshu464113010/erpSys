package cn.erp.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.erp.domain.SaleList;
import cn.erp.service.SaleListService;
import cn.erp.service.impl.SaleListServiceImpl;
import cn.erp.utils.StringUtils;


@WebServlet("/admin/saleList/*")
public class SaleListServlet extends HttpServlet{
	
	private SaleListService saleListService = new SaleListServiceImpl();
	
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
			int i = saleListService.addSaleList(saleList);
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
			
			List<SaleList> list = saleListService.selectByCondition(sale_number,customer_id,state,bSaleDate,eSaleDate);
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
}
