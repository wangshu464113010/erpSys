package cn.erp.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet("/power/upload")
@MultipartConfig
public class UploadServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//创建目录
		String path1 = request.getSession().getServletContext().getRealPath("\\") + "images\\";
        File path = new File(path1);
        if (!path.exists()){
            path.mkdir();
        }
        //获取文件
        Part img = request.getPart("file");
        //文件全路径        
        String cd = img.getHeader("content-disposition");
        //截取不同类型的文件需要自行判断        
        System.out.println("cd=>" +cd);
       String filename = cd.substring(cd.lastIndexOf("=")+2, cd.length()-1);
       System.out.println("filename="+filename);
        String filePath = path.getPath() + File.separator + filename;
        
        System.out.println(img.getName());
        //写入文件
        img.write(filePath);
        //输出信息
        System.out.println("File Upload : " + filePath);
        PrintWriter out = response.getWriter();
        out.write("上传成功");       
        //out.println("File Upload : " + filePath);        
	}	
	/**
	 * 根据请求头解析出文件名
	 * @param header 请求头
	 * @return 文件名
	 */
	public String getFileName(String header) {
		String[] tempArr1 = header.split(";");
		String[] tempArr2 = tempArr1[2].split("=");
		// 获取文件名，兼容各种浏览器
		String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\") + 1).replaceAll("\"", "");
		return fileName;
	}
}
