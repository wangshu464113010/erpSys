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

import cn.erp.domain.Customer;
import cn.erp.domain.Goods;
import cn.erp.domain.SaleListGoods;
import cn.erp.service.CustomerService;
import cn.erp.service.GoodsService;
import cn.erp.service.GoodstypeService;
import cn.erp.service.SaleListGoodsService;
import cn.erp.service.impl.CustomerServiceImpl;
import cn.erp.service.impl.GoodsServiceImpl;
import cn.erp.service.impl.GoodstypeServiceImpl;
import cn.erp.service.impl.SaleListGoodsServiceImpl;

@WebServlet("/admin/customer/*")
public class CustomerServlet extends HttpServlet{
	
	private CustomerService customerService = new CustomerServiceImpl();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/"));
		if("/comboList".equals(uri)){
			showCustomer(req,resp);
		}
	}

	
	
	public void showCustomer(HttpServletRequest req, HttpServletResponse resp){
		try {
			List<Customer> list = customerService.findAll();
			resp.getWriter().write(JSONObject.toJSON(list).toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
