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

import cn.erp.domain.Damagelist;
import cn.erp.domain.Damaggoods;
import cn.erp.service.DamageService;
import cn.erp.service.DamagelistService;
import cn.erp.service.impl.DamageServiceImpl;
import cn.erp.service.impl.DamagelistServiceImpl;

/**
 * Servlet implementation class DamagesServlet
 */
@WebServlet("/admin/damageList/save")
public class DamageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DamageService damageService = new DamageServiceImpl();
	DamagelistService damagelistService = new DamagelistServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		insertDamage_goods(request, response);
		insertDamage_list(request, response);
	}

	private void insertDamage_list(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		String damageDate = request.getParameter("damageDate");// damageDate
		String remarks = request.getParameter("remarks");
		String jsonStr = request.getParameter("goodsJson");
		// System.out.println(jsonStr);
		JSONObject json = JSON.parseObject(jsonStr.substring(1, jsonStr.length() - 1));
		String num = json.getString("num");
		String user_id = "1";
		Damagelist damagelist = new Damagelist();
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void insertDamage_goods(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String jsonStr = request.getParameter("goodsJson");
		// System.out.println(jsonStr);
		JSONObject json = JSON.parseObject(jsonStr.substring(1, jsonStr.length() - 1));
		String code = json.getString("code");
		String model = json.getString("model");
		String name = json.getString("name");
		String num = json.getString("num");
		String price = json.getString("price");
		// System.out.println(price);
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
