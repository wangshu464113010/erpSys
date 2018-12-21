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

import cn.erp.domain.Goods;
import cn.erp.service.GoodstypeService;
import cn.erp.service.ListAlarmService;
import cn.erp.service.impl.GoodstypeServiceImpl;
import cn.erp.service.impl.ListAlarmServiceImpl;
import cn.erp.utils.StringUtils;
@WebServlet(value="/admin1/goods/listAlarm11")
public class ListAlarmServlet extends HttpServlet{
	private ListAlarmService listAlarmService = new ListAlarmServiceImpl();
	private GoodstypeService goodstypeService = new GoodstypeServiceImpl();
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		List<Goods> list=null;
		try {
			list=listAlarmService.findAll();
			String string = JSONObject.toJSON(list).toString();
			StringUtils.removeUnderlineAndUpperCase(string);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
