package com.qy.sp.manage.controller.business;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qy.sp.manage.controller.base.BaseController;
import com.qy.sp.manage.dto.Page;
import com.qy.sp.manage.dto.TCp;
import com.qy.sp.manage.dto.TCp;
import com.qy.sp.manage.dto.TSupplier;
import com.qy.sp.manage.service.CpService;

@Controller
@RequestMapping(value = "/cp")
public class CpController extends BaseController{
	
	@Resource
	private CpService cpService;

	@RequestMapping(value = "/toCpList.do")
	public String toCpList(HttpServletRequest request) throws Exception{
		String pageNumber = request.getParameter("pageNumber")==null?"1":request.getParameter("pageNumber");
		String fullName = stringOf(request.getParameter("fullName"));
		String tel = stringOf(request.getParameter("tel"));
		TCp cp = new TCp();
		cp.setFullName(fullName);
		cp.setTel(tel);
		
		int items = cpService.getCpItems(cp);
		Page page = new Page();
		page.setPageAllCount(items);
		page.setPageNumber(Integer.parseInt(pageNumber));
		List<TCp> cps = cpService.getCpList(cp, page);
		
		request.setAttribute("page", page);
		request.setAttribute("cp", cp);
		request.setAttribute("cps", cps);
		
		return "/cp/listCp";
	}
	
	@RequestMapping(value = "/toAddCp.do")
	public String toAddCp(HttpServletRequest request)  throws Exception{
		return "/cp/updateCp";
	}
	
	@RequestMapping(value = "/toUpdateCpList.do")
	public String toUpdateCpList(HttpServletRequest request)  throws Exception{
		String cpId = stringOf(request.getParameter("cpId"));
		TCp cp = cpService.getCpById(cpId);
		request.setAttribute("cp", cp);
		return "/cp/updateCp";
	}
	
	@RequestMapping(value = "/toUpdateCp.do")
	public String toUpdateSupplierList(HttpServletRequest request)  throws Exception{
		String cpId = stringOf(request.getParameter("cpId"));
		TCp cp = cpService.getCpById(cpId);
		request.setAttribute("cp", cp);
		return "/cp/updateCp";
	}
	
	/**
	 * 验证渠道全称是否已经存在
	 * @throws Exception
	 */
	@RequestMapping(value = "/verifyCpName.do")
	public void verifyCpName(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String fullName = stringOf(new String(request.getParameter("fullName").getBytes("iso-8859-1"),"utf-8"));
		String cpId = request.getParameter("cpId");
		if(!"".equals(cpId) && cpId!=null){
			TCp cp = cpService.getCpById(cpId);
			if(fullName.equals(cp.getFullName())){
				response.getWriter().write("OK");
				return;
			}
		}
		TCp cp = cpService.getCpByFullName(fullName);
		if(cp!=null){
			response.getWriter().write("FAIL");
		}else{
			response.getWriter().write("OK");
		}
	}
	
	@RequestMapping(value = "/doUpdateCp.do")
	public void doUpdateCp(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			String cpId = stringOf(request.getParameter("cpId"));
			String fullName = stringOf(request.getParameter("fullName"));
			String abbrName = stringOf(request.getParameter("abbrName"));
			String contactor = stringOf(request.getParameter("contactor"));
			String tel = stringOf(request.getParameter("tel"));
			String email = stringOf(request.getParameter("email"));
			String qq = stringOf(request.getParameter("qq"));
			TCp cp = new TCp();
			cp.setCpId(cpId);
			cp.setFullName(fullName);
			cp.setAbbrName(abbrName);
			cp.setContactor(contactor);
			cp.setTel(tel);
			cp.setEmail(email);
			cp.setQq(qq);
			cpService.updateCp(cp);
			response.getWriter().write("OK");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("FAIL");
		}
	}
}
