package cn.erp.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.erp.domain.Customer;
import cn.erp.domain.Page;
import cn.erp.service.CustomerService;
import cn.erp.service.impl.CustomerServiceImpl;

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
		if("/list".equals(uri)){
			listPageOrLike(req, resp);
		}
		if("/delete".equals(uri)){
			try {
				String str = req.getParameter("ids").trim();
				String[] split = str.split(",");
				int[] ids = new int[split.length];
				for (int i =0;i<split.length;++i) {
					ids[i]=Integer.parseInt(split[i]);
				}
				if(customerService.delete(ids) > 0){
					resp.getWriter().write("{\"success\":true}");
				}
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("/save".equals(uri)){
			String name = req.getParameter("name");
			String contact = req.getParameter("contact");
			String number = req.getParameter("number");
			String address = req.getParameter("address");
			String remarks = req.getParameter("remarks");
			String id = req.getParameter("id");
			Customer c = new Customer();
			
			c.setContact(contact);
			c.setAddress(address);
			c.setName(name);
			c.setRemarks(remarks);
			c.setNumber(number);
			try {
				if(id == null || id.equals("")){
					customerService.insert(c);
					resp.getWriter().write("{\"success\":true}");
				}else{
					c.setId(Integer.parseInt(id));
					customerService.update(c);
					resp.getWriter().write("{\"success\":true}");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}



	private void listPageOrLike(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String page = req.getParameter("page");
		String pageSize = req.getParameter("rows");
		String name = req.getParameter("name");
		Page<Customer> p = new Page<Customer>();
		p.setPageNow(Integer.parseInt(page));
		p.setSize(Integer.parseInt(pageSize));
		if(name == null || name.equals("")){
			try {
				List<Customer> list = customerService.findPageAll(p);
				Object json = JSONObject.toJSON(list);
				String str = "{\"total\":"+customerService.findAll().size()+",\"rows\":"+json.toString()+"}";
				resp.getWriter().write(str);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			try {
				List<Customer> list = customerService.findLikePageAll(name, p);
				Object json = JSONObject.toJSON(list);
				String str = "{\"total\":"+customerService.countLikeName(name)+",\"rows\":"+json.toString()+"}";
				resp.getWriter().write(str);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
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
