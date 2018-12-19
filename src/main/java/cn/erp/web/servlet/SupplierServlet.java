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

import cn.erp.domain.Supplier;
import cn.erp.service.SupplierService;
import cn.erp.service.impl.SupplierServiceImpl;

/**
 * Servlet implementation class SupplierServlet
 */
@WebServlet("/admin/supplier/*")
public class SupplierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private SupplierService supplierService = new SupplierServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/"));
		if("/list".equals(uri)){//admin/supplier/list
			listPage(request, response);
		}
		if("/delete".equals(uri)){///admin/supplier/delete
			String ids = request.getParameter("ids");
			String[] split = ids.trim().split(",");
			int[] id = new int[split.length];
			for (int i =0;i<split.length;++i) {
				id[i] = Integer.parseInt(split[i]);
			}
			try {
				PrintWriter pw = response.getWriter();
				supplierService.deleteSupplierArray(id);
				pw.write("{\"success\":true}");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if("/save".equals(uri)){//admin/supplier/save
			Supplier supplier = new Supplier();
			String id = request.getParameter("id");
			supplier = injectionSupplier(request, supplier);
			if(id == null){//无主键时，执行保存
				saveSuplier(request, response,supplier);
			}else{//有主键时，执行更新
				int id1 = Integer.parseInt(id);
				supplier.setId(id1);//注入id
				try {
					PrintWriter pw = response.getWriter();
					supplierService.update(supplier);
					pw.write("{\"success\":true}");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}



	private Supplier injectionSupplier(HttpServletRequest request, Supplier supplier) {
		String name = request.getParameter("name");
		String contact = request.getParameter("contact");
		String number = request.getParameter("number");
		String address = request.getParameter("address");
		String remarks = request.getParameter("remarks");
		supplier.setName(name);
		supplier.setContact(contact);
		supplier.setNumber(number);
		supplier.setAddress(address);
		supplier.setRemarks(remarks);
		return supplier;
	}

	private void saveSuplier(HttpServletRequest request, HttpServletResponse response,Supplier supplier) throws IOException {
		PrintWriter pw = response.getWriter();
		try {
			if(supplierService.add(supplier)> 0){
				pw.write("{\"success\":true}");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void listPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String page = request.getParameter("page");
		String row = request.getParameter("rows");
		String name = request.getParameter("name");
		PrintWriter pw = response.getWriter();
		try {
			int pageNow = 1;
			int pageSize =10;
			if(page != null){
				pageNow = Integer.parseInt(page);
			}if(row != null){
				pageSize = Integer.parseInt(row);
			}
			if(name == null){
				String s = supplierService.getSupplierListForPageToString(pageNow, pageSize);
				pw.write(s);
			}else{
				String s = supplierService.likeQuerySupplierListForPageToString(pageNow, pageSize,name);
				pw.write(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
