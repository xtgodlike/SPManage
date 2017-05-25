package com.qy.sp.manage.controller.business;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qy.sp.manage.controller.base.BaseController;
import com.qy.sp.manage.dto.Page;
import com.qy.sp.manage.dto.TSupplier;
import com.qy.sp.manage.service.SupplierService;

@Controller
@RequestMapping(value = "/supplier")
public class SupplierController extends BaseController{
	
	@Resource
	private SupplierService supplierService;

	@RequestMapping(value = "/toSupplierList.do")
	public String toSupplierList(HttpServletRequest request) throws Exception{
		String pageNumber = request.getParameter("pageNumber")==null?"1":request.getParameter("pageNumber");
		String fullName = stringOf(request.getParameter("fullName"));
		String tel = stringOf(request.getParameter("tel"));
		TSupplier supplier = new TSupplier();
		supplier.setFullName(fullName);
		supplier.setTel(tel);
		
		int items = supplierService.getSupplierItems(supplier);
		Page page = new Page();
		page.setPageAllCount(items);
		page.setPageNumber(Integer.parseInt(pageNumber));
		List<TSupplier> suppliers = supplierService.getSupplierList(supplier, page);
		
		request.setAttribute("page", page);
		request.setAttribute("supplier", supplier);
		request.setAttribute("suppliers", suppliers);
		
		return "/supplier/listSupplier";
	}
	
	@RequestMapping(value = "/toAddSupplier.do")
	public String toAddSupplier(HttpServletRequest request)  throws Exception{
		return "/supplier/updateSupplier";
	}
	
	@RequestMapping(value = "/toUpdateSupplierList.do")
	public String toUpdateSupplierList(HttpServletRequest request)  throws Exception{
		String supplierId = stringOf(request.getParameter("supplierId"));
		TSupplier supplier = supplierService.getSupplierById(supplierId);
		request.setAttribute("supplier", supplier);
		return "/supplier/updateSupplier";
	}
	
	/**
	 * 验证渠道全称是否已经存在
	 * @throws Exception
	 */
	@RequestMapping(value = "/verifySupplierName.do")
	public void verifySupplierName(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String fullName = stringOf(new String(request.getParameter("fullName").getBytes("iso-8859-1"),"utf-8"));
		String supplierId = request.getParameter("supplierId");
		if(!"".equals(supplierId) && supplierId!=null){
			TSupplier supplier = supplierService.getSupplierById(supplierId);
			if(fullName.equals(supplier.getFullName())){
				response.getWriter().write("OK");
				return;
			}
		}
		TSupplier supplier = supplierService.getSupplierByFullName(fullName);
		if(supplier!=null){
			response.getWriter().write("FAIL");
		}else{
			response.getWriter().write("OK");
		}
	}
	
	@RequestMapping(value = "/doUpdateSupplier.do")
	public void doUpdateSupplier(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			String supplierId = stringOf(request.getParameter("supplierId"));
			String fullName = stringOf(request.getParameter("fullName"));
			String abbrName = stringOf(request.getParameter("abbrName"));
			String contactor = stringOf(request.getParameter("contactor"));
			String tel = stringOf(request.getParameter("tel"));
			String email = stringOf(request.getParameter("email"));
			String qq = stringOf(request.getParameter("qq"));
			TSupplier supplier = new TSupplier();
			supplier.setSupplierId(supplierId);
			supplier.setFullName(fullName);
			supplier.setAbbrName(abbrName);
			supplier.setContactor(contactor);
			supplier.setTel(tel);
			supplier.setEmail(email);
			supplier.setQq(qq);
			supplierService.updateTSupplier(supplier);
			response.getWriter().write("OK");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("FAIL");
		}
	}
}
