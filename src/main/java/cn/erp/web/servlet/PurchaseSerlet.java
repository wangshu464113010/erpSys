package cn.erp.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import cn.erp.domain.GoodsJson;
import cn.erp.domain.ProcurementStatisticsVO;
import cn.erp.domain.PurchaseList;
import cn.erp.domain.Purchase_List;
import cn.erp.domain.Purchase_List_Goods;
import cn.erp.service.ProcurementStatisticsVOService;
import cn.erp.domain.User;
import cn.erp.service.GoodstypeService;
import cn.erp.service.PurchaseService;
import cn.erp.service.impl.ProcurementStatisticsVOServiceImpl;
import cn.erp.service.impl.PurchaseServiceImpl;
import cn.erp.service.impl.SupplierServiceImpl;
import cn.erp.utils.LogUtils;
import utils.StringUtils;


/**
 * 
 * @author wangshu
 *
 */
@WebServlet("/admin/purchaseList/*")
public class PurchaseSerlet extends HttpServlet{
	
	private PurchaseService purchaseService = new PurchaseServiceImpl();
	private ProcurementStatisticsVOService procurementStatisticsVOService = new ProcurementStatisticsVOServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/"));
		//uri = uri.substring(6);
//		if("/goodsType/loadTreeInfo".equals(uri)){
//			findAll(request,response);//
//		}
//		if("/goodsType/list".equals(uri)){
//			findAllByType(request,response);//
//		}
		if("/listCount".equals(uri)){
			String bPurchaseDate = request.getParameter("bPurchaseDate");
			String ePurchaseDate = request.getParameter("ePurchaseDate");
			String type = request.getParameter("type.id");
			String codeOrName = request.getParameter("codeOrName");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				List<ProcurementStatisticsVO> list = procurementStatisticsVOService.findAll(
						sdf.parse(bPurchaseDate), 
						sdf.parse(ePurchaseDate), type, codeOrName);
				String string = JSONObject.toJSON(list).toString();
				response.getWriter().write("{\"rows\":"+string+"}");
			} catch (SQLException | ParseException e) {
				e.printStackTrace();
			}
			
			
		}
		
		//uri = uri.substring(uri.lastIndexOf("/"));	
		if("/save".equals(uri)){
			saveSupplier(request,response);
		}
		if("/list".equals(uri)){
			findAllPurchaseSearch(request,response);
		}
		if("/listGoods".equals(uri)){
			findAllListGoods(request,response);
		}
		if("/delete".equals(uri)){
			deletePurchaseList(request,response);
		}
		if("/update".equals(uri)){
			updateState(request,response);
		}
		
		if("/getPurchaseNumber".equals(uri)){
			getPurchaseNumber(request,response);
		}	
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}	
	private void findAllByType(HttpServletRequest request, HttpServletResponse response) throws IOException {		
		PrintWriter pw = response.getWriter();
		try {
			List<PurchaseList> list = purchaseService.purchaseList();
			Object json = JSONObject.toJSON(list);
			pw.write(json.toString().replaceAll("name", "text"));
			User u = (User) request.getSession().getAttribute("user");
			LogUtils.insertLog("鏌ヨ鎿嶄綔", "鏌ヨ鎵�鏈夊晢鍝佺绫�",u.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void saveSupplier(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter pw = response.getWriter();
		Integer user_id=1;
		String supplier_id = request.getParameter("supplier.id");
		float amount_payable = Float.parseFloat(request.getParameter("amountPayable"));
		float amount_paid = Float.parseFloat(request.getParameter("amountPaid"));
		String purchaseDate = request.getParameter("purchaseDate");
		String purchase_Number=request.getParameter("purchaseNumber");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date purchase_date=null;
		try {
			purchase_date = sdf.parse(purchaseDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String remarks = request.getParameter("remarks");
		int state = Integer.parseInt(request.getParameter("state"));
		String json = request.getParameter("goodsJson");
		ArrayList<GoodsJson> list = JSON.parseObject(json, new TypeReference<ArrayList<GoodsJson>>() {});
		try {
			int i = this.purchaseService.saveJh(user_id, supplier_id, amount_payable, amount_paid, purchase_date, remarks, state, list, purchase_Number);
			Map<String,Object> map=new HashMap<>();
			Object json1=null;
			if(i==list.size()+1){
				map.put("success", true);
				json1 = JSONObject.toJSON(map);
				User u = (User) request.getSession().getAttribute("user");
				LogUtils.insertLog("娣诲姞鎿嶄綔", "娣诲姞渚涘簲鍟嗕俊鎭�",u.getId());
			}else{
				map.put("success", false);
				map.put("errorInfo", "绯荤粺绻佸繖锛岃绋嶅悗灏濊瘯");
				json1 = JSONObject.toJSON(map);
			}
			pw.write(json1.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void findAllPurchaseSearch(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		String purchaseNumber = request.getParameter("purchaseNumber");
		Integer supplier_id=null;
		if(!"".equals(request.getParameter("supplier.id"))&&request.getParameter("supplier.id")!=null){
			supplier_id=Integer.parseInt(request.getParameter("supplier.id"));
		}
		Integer state=null;
		if(!"".equals(request.getParameter("state"))&&request.getParameter("state")!=null){
			state = Integer.parseInt(request.getParameter("state"));
		}
		String bPurchaseDate = request.getParameter("bPurchaseDate");
		String ePurchaseDate = request.getParameter("ePurchaseDate");
		try {
			List<Purchase_List> list = null;
			if(purchaseNumber == null){
				list = this.purchaseService.findAllPurchaseSearch("",supplier_id,state,bPurchaseDate,ePurchaseDate);
			}else{
				list = this.purchaseService.findAllPurchaseSearch(purchaseNumber,supplier_id,state,bPurchaseDate,ePurchaseDate);
			}
			Map<String,Object> map=new HashMap<>();
			map.put("rows", list);
			Object json = JSONObject.toJSON(map);
			String str = json.toString();
			str = StringUtils.removeUnderlineAndUpperCase(str);
			pw.write(str);
			User u = (User) request.getSession().getAttribute("user");
			LogUtils.insertLog("鏌ヨ鎿嶄綔", "鏌ヨ鎵�鏈夎繘璐т俊鎭�",u.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void findAllListGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		Integer id=Integer.parseInt(request.getParameter("purchaseListId"));
		try {
			List<Purchase_List_Goods> list = this.purchaseService.findAllListGoodsById(id);
			Map<String,Object> map=new HashMap<>();
			map.put("rows", list);
			Object json = JSONObject.toJSON(map);
			String str = json.toString();
			str = StringUtils.removeUnderlineAndUpperCase(str);
			pw.write(str);
			User u = (User) request.getSession().getAttribute("user");
			LogUtils.insertLog("鏌ヨ鎿嶄綔", "鏌ヨ鎵�鏈夊晢鍝佷俊鎭�",u.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	private void deletePurchaseList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		Integer id = Integer.parseInt(request.getParameter("id"));
		try {
			int index = this.purchaseService.deletePurchaseList(id);
			Map<String,Object> map=new HashMap<>();
			Object json1=null;
			if(index>0){
				map.put("success", true);
				User u = (User) request.getSession().getAttribute("user");
				LogUtils.insertLog("鍒犻櫎鎿嶄綔", "鍒犻櫎瀹㈡埛鍗曚俊鎭�",u.getId());
				json1 = JSONObject.toJSON(map);
			}else{
				map.put("success", false);
				map.put("errorInfo", "绯荤粺绻佸繖锛岃绋嶅悗灏濊瘯");
				json1 = JSONObject.toJSON(map);
			}
			pw.write(json1.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	private void updateState(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			String id = req.getParameter("id");
			int i = purchaseService.updataState(Integer.parseInt(id));
			Map<String, Object> map = new HashMap<>();
			if(i == 0){
				map.put("errorInfo", "鎿嶄綔瓒呮椂锛�");
			}else{
				map.put("success", true);
			}
			resp.getWriter().write(JSONObject.toJSON(map).toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void getPurchaseNumber(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		Date date =new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		try {
			String purchaseNumber = this.purchaseService.getPurchaseNumber(date1);
			pw.write(purchaseNumber);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
