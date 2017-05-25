package com.qy.sp.manage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qy.sp.manage.common.utils.Global;

public class ControllerFilter implements Filter {


	public void destroy() {
		
	}
	

	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		
		String loginPath = req.getContextPath()+"/basics/toLogin.do";		
		String requestUrl = req.getRequestURI();
		
		if(req.getSession().getAttribute(Global.U_SESSION) == null){
			if(!verifyRequest(requestUrl,req.getContextPath())){
				res.sendRedirect(loginPath);
				return;
			}
		}
		chain.doFilter(request, response);
	}
	
	
	private boolean verifyRequest(String requestUrl,String path){
		List<String> list = new ArrayList<String>();
		list.add(path+"/basics/toLogin.do");
		list.add(path+"/basics/logOut.do");
		list.add(path+"/basics/doLogin.do");
		list.add(path+"/basics/sysError.do");
		boolean flag = false;
		if(list.contains(requestUrl)){
			flag = true;			
		}
		return flag;
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
