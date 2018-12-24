package cn.erp.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mysql.fabric.xmlrpc.base.Data;

import cn.erp.domain.Overgoods;
import cn.erp.domain.Overlist;
import cn.erp.domain.User;
import cn.erp.service.OverService;
import cn.erp.service.OverlistService;
import cn.erp.service.impl.OverServiceImpl;
import cn.erp.service.impl.OverlistServiceImpl;
import cn.erp.utils.LogUtils;

/**
 * 张潜
 * Servlet implementation class OverServlet
 */
@WebServlet("/admin/overflowList/save")
public class OverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	OverService overService = new OverServiceImpl();
    OverlistService overlistService = new OverlistServiceImpl();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			try {
				insertOver_list(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			insertOver_goods(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  //向商品溢出关联表里面添加信息
	private String insertOver_list(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, ParseException, SQLException {
		String overflowDate = request.getParameter("overflowDate");// overflowDate		
		String remarks = request.getParameter("remarks");
		String jsonStr = request.getParameter("goodsJson");
		JSONObject json = JSON.parseObject(jsonStr.substring(1, jsonStr.length() - 1));		
		String num = json.getString("num");		
		String overflowNumber = "BY" +overflowDate.replaceAll("-", "")+""+num;
		String user_id = "1";//没有登录，默认值值为一
		Overlist overlist = new Overlist();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			overlist.setOverflow_date(sdf.parse(overflowDate));
			User u = (User) request.getSession().getAttribute("user");
			LogUtils.insertLog("添加操作", "向商品溢出关联表插入信息",u.getId());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		overlist.setOverflow_number(overflowNumber);
		overlist.setRemarks(remarks);
		overlist.setUser_id(1);// 还没有登录功能，默认为一
		try {
			overlistService.insertOverlist(overlist);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//插入商品溢出表
	private void insertOver_goods(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String jsonStr = request.getParameter("goodsJson");
		JSONObject json = JSON.parseObject(jsonStr.substring(1, jsonStr.length() - 1));
		String code = json.getString("code");
		String model = json.getString("model");
		String name = json.getString("name");
		String num = json.getString("num");
		String price = json.getString("price");
		String total = json.getString("total");
		String unit = json.getString("unit");
		String type_id = json.getString("typeId");
		String goods_id = json.getString("goodsId");
		Overgoods overgoods = new Overgoods();
		overgoods.setCode(Integer.parseInt(code));
		overgoods.setModel(model);
		overgoods.setName(name);
		overgoods.setNum(Integer.parseInt(num));
		overgoods.setPrice(Double.parseDouble(price));
		overgoods.setTotal(Double.parseDouble(total));
		overgoods.setUnit(unit);
		Overlist overlist=new Overlist();
		Integer maxId=null;
		try {
			maxId=overlistService.getMaxId(overlist);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}		
		overgoods.setType_id(type_id);
		overgoods.setGoods_id(Integer.parseInt(goods_id));
		overgoods.setOverflow_list_id(maxId);		
		try {
			overService.insertover(overgoods);
			PrintWriter pw = response.getWriter();
			pw.write("{\"success\":true}");
			User u = (User) request.getSession().getAttribute("user");
			LogUtils.insertLog("添加操作", "向商品溢出表插入信息",u.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
