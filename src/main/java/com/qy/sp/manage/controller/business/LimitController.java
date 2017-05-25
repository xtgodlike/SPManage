package com.qy.sp.manage.controller.business;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qy.sp.manage.controller.base.BaseController;
import com.qy.sp.manage.service.LimitService;


@Controller
@RequestMapping("/limit")
public class LimitController extends BaseController{

	@Resource
	private LimitService limitService;
	
	@RequestMapping(value = "/toMobileBlackList.do")
	public String toMobileBlackList(HttpServletRequest request)  throws Exception{
		return "/limit/updateMobileBlackList";
	}
	
	@RequestMapping(value = "/doUpdateMobileBlackList.do")
	public void doUpdateMobileBlackList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			String opType = stringOf(request.getParameter("opType"));
			String  mobiles = stringOf(request.getParameter("mobiles"));
			String[] mobilex = mobiles.trim().split(",");
			List<String> mobileList = Arrays.asList(mobilex);
			String returnMsg= limitService.updateMoblieBlackList(mobileList,opType);
			response.getWriter().write(returnMsg);
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write(e.getMessage());
		}
	}
}
