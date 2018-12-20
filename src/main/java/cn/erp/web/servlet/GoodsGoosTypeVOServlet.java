package cn.erp.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.management.relation.RelationSupportMBean;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.erp.domain.GoodsGoosTypeVO;
import cn.erp.domain.Page;
import cn.erp.service.GoodsGoosTypeVOService;
import cn.erp.service.impl.GoodsGoosTypeVOServiceImpl;
import cn.erp.utils.StringUtils;

/**
 * author:张潜
 * function:期初库存
 * Servlet implementation class goodsGoosTypeVOServlet
 */
@WebServlet("/admin/goods/listNoInventoryQuantity")
public class GoodsGoosTypeVOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	GoodsGoosTypeVOService goodsGoosTypeVOService=new GoodsGoosTypeVOServiceImpl();
	@Override
		protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			if(request.getParameter("codeOrName") == null ||request.getParameter("codeOrName").equals("")) {
				GoodsGoosTypeVOFindall(request, response);
			}else {
				GoodsGoosTypeVOFindlike(request, response);
			}
		}
	private void GoodsGoosTypeVOFindlike(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String page = request.getParameter("page");//--->http打包的数据 ---->html页面
		String rows = request.getParameter("rows");
		String codeOrName = request.getParameter("codeOrName");
		Page<GoodsGoosTypeVO> page1 = new Page<GoodsGoosTypeVO>();		
		page1.setSize(Integer.parseInt(rows));			
		page1.setPageNow(Integer.parseInt(page));
		try {
			//data来源于service--->dao----->database
			List<GoodsGoosTypeVO> list = null;
			list = goodsGoosTypeVOService.findlike(page1,codeOrName);//把请求响应
			String string = JSONObject.toJSON(list).toString();
			int total = goodsGoosTypeVOService.countLike(codeOrName);
			string = "{\"total\":"+total+",\"rows\":"+string+"}";			
			response.getWriter().write(string);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void GoodsGoosTypeVOFindall(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		Page<GoodsGoosTypeVO> page1 = new Page<GoodsGoosTypeVO>();
		
		page1.setSize(Integer.parseInt(rows));			
		page1.setPageNow(Integer.parseInt(page));
		
		try {
			//data来源于service--->dao----->database
			List<GoodsGoosTypeVO> list = goodsGoosTypeVOService.findAll(page1);//把请求响应
			String string = JSONObject.toJSON(list).toString();
			int total = goodsGoosTypeVOService.count();
			string = "{\"total\":"+total+",\"rows\":"+string+"}";
			response.getWriter().write(string);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
