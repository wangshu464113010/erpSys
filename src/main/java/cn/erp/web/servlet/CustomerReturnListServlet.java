package cn.erp.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.erp.domain.CustomerReturnList;
import cn.erp.domain.CustomerReturnListCount;
import cn.erp.domain.CustomerReturnListGoods;
import cn.erp.service.CustomerReturnListService;
import cn.erp.service.impl.CustomerReturnListServiceImpl;
import cn.erp.utils.StringUtils;

@WebServlet("/admin/customerReturnList/*")
public class CustomerReturnListServlet extends HttpServlet {
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
		}
	}

	// 客户退货单号信息
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
			String string = StringUtils.removeUnderlineAndUpperCase(jsonData);
			pw.write(string);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 客户具体信息单号信息
	private void findCustomerReturnListGoodsAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter pw = resp.getWriter();
		// 拿到页面的值
		Integer customer_return_list_id = Integer.parseInt(req.getParameter("customerReturnListId"));
		try {
			List<CustomerReturnListGoods> list = null;
			list = customerReturnListService.findCustomerReturnListGoodsAll(customer_return_list_id);
			String jsonData = JSONObject.toJSON(list).toString();
			jsonData = "{\"rows\":" + jsonData + "}";
			String string = StringUtils.removeUnderlineAndUpperCase(jsonData);
			pw.write(string);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void customerReturnListCount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			String bCustomerReturnDate = req.getParameter("bCustomerReturnDate");
			String eCustomerReturnDate = req.getParameter("eCustomerReturnDate");
			Integer type_id = null;
			if (!"".equals(req.getParameter("type.id")) && req.getParameter("type.id") != null) {
				type_id = Integer.parseInt(req.getParameter("type.id"));
			}
			String codeOrName = req.getParameter("codeOrName");
			List<CustomerReturnListCount> list = customerReturnListService.findListCount(bCustomerReturnDate, eCustomerReturnDate, type_id, codeOrName);
			String jsonData = JSONObject.toJSON(list).toString();
			jsonData = "{\"rows\":" + jsonData + "}";
			String string = StringUtils.removeUnderlineAndUpperCase(jsonData);
			resp.getWriter().write(string);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
