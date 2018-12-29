package cn.erp.web.servlet;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.erp.domain.Goods;
import cn.erp.domain.GoodsType;
import cn.erp.service.GoodsService;
import cn.erp.service.impl.GoodsServiceImpl;

@WebServlet("/common/download")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private GoodsService goodsService =  new GoodsServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
		
		try {
			List<Goods> list = goodsService.findAll();//获取所有数据,表达数据
//			for (Goods goods : list) {
//				System.out.println(goods.toString());
//			}
			String path = request.getSession().getServletContext().getRealPath("\\") + "make\\";
			InputStream is = new FileInputStream(new File(path + "a.xlsx"));
			System.out.println(path+ "a.xlsx");
			
			//Workbook wb = new HSSFWorkbook(is);//打开一个模板文件

			Workbook wb = new XSSFWorkbook(is);
			Sheet sheet = wb.getSheetAt(0);
			Row nRow = null;
			Cell nCell = null;
			int rowNo = 0 ;//行号
			int colNo = 0 ;//列号

			
			nRow = sheet.getRow(rowNo++);//第一行/0
			
			nCell = nRow.getCell(1);
			CellStyle cellStyle = nCell.getCellStyle();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年-MM月的");
			String format = sdf.format(new Date());
			
			nCell.setCellValue(format+"当前库存查询");
			nCell.setCellStyle(cellStyle);
			
			rowNo++;
			
			nRow = sheet.getRow(1);
			
			nCell = nRow.getCell(1);
			CellStyle codeStyle = nCell.getCellStyle();//code样式 编号
			
			nCell = nRow.getCell(2);
			CellStyle nameStyle = nCell.getCellStyle();//name样式 商品名称
		
			nCell = nRow.getCell(3);
			CellStyle typeStyle = nCell.getCellStyle();//type样式  类别 
			
			nCell = nRow.getCell(4);
			CellStyle modelStyle = nCell.getCellStyle();//model样式 型号
			
			nCell = nRow.getCell(5);
			CellStyle  inventoryQuantityStyle = nCell.getCellStyle();//inventoryQuantity样式  库存数量
			
			nCell = nRow.getCell(6);
			CellStyle  saleTotaStyle = nCell.getCellStyle();//saleTota样式  库存总量
			
			nCell = nRow.getCell(7);
			CellStyle lastPurchasingPriceStyle = nCell.getCellStyle();// lastPurchasingPriceStyle样式  上一次进价量
			
			nCell = nRow.getCell(8);
			CellStyle  purchasingPricStyle = nCell.getCellStyle();//purchasingPricStyle样式  成本进价
			
			nCell = nRow.getCell(9);
			CellStyle  sellingPriceStyle = nCell.getCellStyle();//sellingPriceStyle样式  销售价
			
			nCell = nRow.getCell(10);
			CellStyle  totaStyle = nCell.getCellStyle();//样式  库存总量
			
			nCell = nRow.getCell(11);
			CellStyle  unitStyle = nCell.getCellStyle();//unitStyle  单位
			
			nCell = nRow.getCell(12);
			CellStyle   producerStyle = nCell.getCellStyle();//样式  库存总量
			
			for(int i=0;i<list.size();++i){
				
				colNo = 0;
				
				nRow = sheet.createRow(rowNo++);//第3...n行
				
				nCell = nRow.createCell(++colNo);
				nCell.setCellValue(list.get(i).getCode());
				nCell.setCellStyle(codeStyle);
				
				nCell = nRow.createCell(++colNo);
				nCell.setCellValue(list.get(i).getName());
				nCell.setCellStyle(nameStyle);
				
				nCell = nRow.createCell(++colNo);
				nCell.setCellValue(list.get(i).getType().getName());
				nCell.setCellStyle(typeStyle);
				
				nCell = nRow.createCell(++colNo);
				nCell.setCellValue(list.get(i).getModel());
				nCell.setCellStyle(modelStyle);
				
				//---------
				nCell = nRow.createCell(++colNo);
				nCell.setCellValue(list.get(i).getInventory_quantity());
				nCell.setCellStyle(inventoryQuantityStyle);
				
				nCell = nRow.createCell(++colNo);
				nCell.setCellValue(list.get(i).getSaleTotal());
				nCell.setCellStyle(saleTotaStyle);
				
				nCell = nRow.createCell(++colNo);
				nCell.setCellValue(list.get(i).getLast_purchasing_price());
				nCell.setCellStyle(lastPurchasingPriceStyle);
				
				nCell = nRow.createCell(++colNo);
				nCell.setCellValue(list.get(i).getPurchasing_price());
				nCell.setCellStyle(purchasingPricStyle);
				
				//--
				nCell = nRow.createCell(++colNo);
				nCell.setCellValue(list.get(i).getSelling_price());
				nCell.setCellStyle(sellingPriceStyle);
				
				nCell = nRow.createCell(++colNo);
				nCell.setCellValue("库存总值");
				nCell.setCellStyle(totaStyle);
				
				nCell = nRow.createCell(++colNo);
				nCell.setCellValue(list.get(i).getUnit());
				nCell.setCellStyle(unitStyle);
				
				nCell = nRow.createCell(++colNo);
				nCell.setCellValue(list.get(i).getProducer());
				nCell.setCellStyle(producerStyle);			
			}	
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			
			wb.write(os);
			String returnName = "chukubiao.xlsx";
			response.setCharacterEncoding("UTF-8");

			response.setContentType("application/octet-stream;charset=utf-8");
			
			String header = request.getHeader("User-Agent").toUpperCase();
			if (header.indexOf("FIREFOX") > 0){
				returnName = new String(returnName.getBytes("UTF-8"), "ISO8859-1");
			} else if (header.indexOf("MSIE") > 0){
				returnName = URLEncoder.encode(returnName, "UTF-8");// ie
			}

			//returnName = response.encodeURL(new String(returnName.getBytes("UTF-8"),"iso8859-1"));			//保存的文件名,必须和页面编码一致,否则乱码
			//returnName = URLEncoder.encode(returnName, "UTF-8");
			response.addHeader("Content-Disposition",   "attachment;filename=" + returnName);  
			response.setContentLength(os.size());
			
			ServletOutputStream outputstream = response.getOutputStream();	//取得输出流
			os.writeTo(outputstream);					//写到输出流
			os.close();									//关闭
			outputstream.flush();		

		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
