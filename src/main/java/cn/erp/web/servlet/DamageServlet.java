package cn.erp.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.erp.domain.DamageList;
import cn.erp.domain.DamageListGoods;
import cn.erp.domain.Damaggoods;
import cn.erp.service.DamageGoodsService;
import cn.erp.domain.User;
import cn.erp.service.DamageService;
import cn.erp.service.DamagelistService;
import cn.erp.service.impl.DamageGoodsServiceImpl;
import cn.erp.service.impl.DamageServiceImpl;
import cn.erp.service.impl.DamagelistServiceImpl;
import cn.erp.utils.StringUtils;
import cn.erp.utils.LogUtils;

/**
 * Servlet implementation class DamagesServlet
 */
@WebServlet("/admin/damageList/*")
public class DamageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DamageService damageService = new DamageServiceImpl();
	DamagelistService damagelistService = new DamagelistServiceImpl();
	
	private DamageGoodsService damageGoodsService=new DamageGoodsServiceImpl();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/"));
		
		if ("/list".equals(uri)) {
			findAllDamage(request, response);
		}
		if("/listGoods".equals(uri)) {
			String damageListId =request.getParameter("damageListId");
			int damageListid = Integer.parseInt(damageListId);
			List<DamageListGoods> list=null;
			try {
				list=damageGoodsService.findBydamage_list_id(damageListid);
				String string = JSONObject.toJSON(list).toString();
//			System.out.println(string);
				string = "{\"rows\":"+string+"}";
				string = StringUtils.removeUnderlineAndUpperCase(string);
				response.getWriter().write(string);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if ("/save".equals(uri)) {
			
			insertDamage_goods(request, response);
			insertDamage_list(request, response);
		}
	}
	//向报损关联表添加信息
	private void insertDamage_list(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		String damageDate = request.getParameter("damageDate");// damageDate
		String remarks = request.getParameter("remarks");
		String jsonStr = request.getParameter("goodsJson");
		JSONObject json = JSON.parseObject(jsonStr.substring(1, jsonStr.length() - 1));
		String num = json.getString("num");
		String user_id = "1";
		DamageList damagelist = new DamageList();
		String damageNumber = "BS" + damageDate.replaceAll("-", "") + "" + num;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			damagelist.setDamage_date(sdf.parse(damageDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		damagelist.setDamage_number(damageNumber);
		damagelist.setRemarks(remarks);
		damagelist.setUser_id(1);// 还没有登录功能，默认为一
		try {
			damagelistService.insertDamagelist(damagelist);
			User u = (User) request.getSession().getAttribute("user");
			LogUtils.insertLog("添加操作", "添加一条商品损坏关联信息",u.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void findAllDamage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String bDamageDate = req.getParameter("bDamageDate");
		String eDamageDate = req.getParameter("eDamageDate");
		List<DamageList> list=null;
		try {
			Date BDamageDate = new java.sql.Date(format(bDamageDate).getTime());
			Date EDamageDate = new java.sql.Date(format(eDamageDate).getTime());
			list=damageService.findAll(BDamageDate,EDamageDate);
			String string = JSONObject.toJSON(list).toString();
			string = "{\"rows\":"+string+"}";
			string = StringUtils.removeUnderlineAndUpperCase(string);
			resp.getWriter().write(string);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static java.util.Date format(String str) throws ParseException {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    java.util.Date result = sdf.parse(str);
	    return   result;
	}
	//向报损表里面添加信息
	private void insertDamage_goods(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String jsonStr = request.getParameter("goodsJson");
		JSONObject json = JSON.parseObject(jsonStr.substring(1, jsonStr.length() - 1));
		String code = json.getString("code");
		String model = json.getString("model");
		String name = json.getString("name");
		String num = json.getString("num");
		String price = json.getString("price");
		String total = json.getString("total");
		String unit = json.getString("unit");
//	String  damage_list_id= json.getString("damageListId");
		String type_id = json.getString("typeId");
		String goods_id = json.getString("goodsId");
		Damaggoods damaggoods = new Damaggoods();
		damaggoods.setCode(Integer.parseInt(code));
		damaggoods.setModel(model);
		damaggoods.setName(name);
		damaggoods.setNum(Integer.parseInt(num));
		damaggoods.setPrice(Double.parseDouble(price));
		damaggoods.setTotal(Double.parseDouble(total));
		damaggoods.setUnit(unit);
		// damaggoods.setDamage_list_id(Integer.parseInt(damage_list_id));
		damaggoods.setType_id(type_id);
		damaggoods.setGoods_id(Integer.parseInt(goods_id));
		try {
			damageService.insertdamage(damaggoods);
			PrintWriter pw = response.getWriter();
			pw.write("{\"success\":true}");
			User u = (User) request.getSession().getAttribute("user");
			LogUtils.insertLog("添加操作", "添加一条商品损坏信息",u.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
