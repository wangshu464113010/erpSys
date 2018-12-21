package cn.erp.web.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.erp.domain.DamageList;
import cn.erp.domain.DamageListGoods;
import cn.erp.service.DamageGoodsService;
import cn.erp.service.DamageService;
import cn.erp.service.impl.DamageGoodsServiceImpl;
import cn.erp.service.impl.DamageServiceImpl;
import cn.erp.utils.StringUtils;
@WebServlet(value="/admin/damageList/*")
public class DamageServlet extends HttpServlet{
	
	private DamageService damageService = new DamageServiceImpl();
	private DamageGoodsService damageGoodsService=new DamageGoodsServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/"));
		
		if ("/list".equals(uri)) {
			findAllDamage(req, resp);
		}
		if("/listGoods".equals(uri)) {
			String damageListId =req.getParameter("damageListId");
			int damageListid = Integer.parseInt(damageListId);
			List<DamageListGoods> list=null;
			try {
				list=damageGoodsService.findBydamage_list_id(damageListid);
				String string = JSONObject.toJSON(list).toString();
//			System.out.println(string);
				string = "{\"rows\":"+string+"}";
				string = StringUtils.removeUnderlineAndUpperCase(string);
				resp.getWriter().write(string);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
}
