package com.erp.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class ImageFilter
 */
@WebFilter("/user/login")
public class ImageFilter implements Filter {



	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		String str = (String) session.getAttribute("checkcode");
		String userStr = req.getParameter("imageCode");
		if(session.getAttribute("user")!=null) {
			session.setAttribute("checkcode", null);
			req.getRequestDispatcher("user/login").forward(req, resp);
			return ;
		}
		if(str.equals(userStr)) {
			session.setAttribute("checkcode", null);
			chain.doFilter(request, response);
		}else {
			resp.getWriter().write("{\"success\":false,\"errorInfo\":\"验证码输入错误！\"}");
			return;
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
