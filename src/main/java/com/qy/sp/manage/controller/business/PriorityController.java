package com.qy.sp.manage.controller.business;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qy.sp.manage.common.utils.Global;
import com.qy.sp.manage.controller.base.BaseController;
import com.qy.sp.manage.dto.PiplePriority;
import com.qy.sp.manage.dto.TDic;
import com.qy.sp.manage.dto.THost;
import com.qy.sp.manage.dto.TPipleProvince;
import com.qy.sp.manage.dto.TProvince;
import com.qy.sp.manage.service.DicService;
import com.qy.sp.manage.service.PipleService;
import com.qy.sp.manage.service.PriorityService;

@Controller
@RequestMapping(value = "/priority")
public class PriorityController  extends BaseController{

	@Resource
	private PriorityService priorityService;
	@Resource
	private DicService dicService;
	@Resource
	private PipleService pipleService;
	
	@RequestMapping(value = "/toProvinceList.do")
	public String toProvinceList(HttpServletRequest request) throws Exception{
		Integer hostId = isEmpty(request.getParameter("hostId"))?null:Integer.valueOf(request.getParameter("hostId"));
//		Integer provinceId = isEmpty(request.getParameter("provinceId"))?null:Integer.valueOf(request.getParameter("provinceId"));
		THost host = priorityService.getHostById(hostId);
		List<THost> hList = dicService.getAllHosts();
		List<TProvince>  provinces =  dicService.getAllProvinces();
		
		request.setAttribute("hostId", hostId);
		request.setAttribute("host", host);
		request.setAttribute("hList", hList);
		request.setAttribute("provinces", provinces);
		
		return "/priority/listProvince";
	}
	
	@RequestMapping(value = "/toPiplePriorityList.do")	
	public String toPiplePriorityList(HttpServletRequest request) throws Exception{
		Integer hostId = Integer.valueOf(request.getParameter("hostId"));
		Integer provinceId = Integer.valueOf(request.getParameter("provinceId"));
		PiplePriority piplePriority = new PiplePriority();
		piplePriority.setHostId(hostId);
		piplePriority.setProvinceId(provinceId);
		List<PiplePriority> pList = priorityService.getPiplePriorityList(piplePriority);
		THost host = priorityService.getHostById(hostId);
		TProvince province = priorityService.getProvinceById(provinceId);
		List<TDic> pipleTypes = dicService.getDicsForDTypeId(Global.DicID.PIPLE_TYPE);
		request.setAttribute("pList", pList);
		request.setAttribute("host", host);
		request.setAttribute("province", province);
		request.setAttribute("pipleTypes", pipleTypes);
		return "/priority/listPiplePriority";
	}
	
	@RequestMapping(value = "/doUpdatePiplePriority.do")
	public void doUpdatePipleProvince(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			Integer hostId = Integer.valueOf(request.getParameter("hostId"));
			Integer provinceId = Integer.valueOf(request.getParameter("provinceId"));
			PiplePriority piplePriority = new PiplePriority();
			piplePriority.setHostId(hostId);
			piplePriority.setProvinceId(provinceId);
			List<PiplePriority> pList = priorityService.getPiplePriorityList(piplePriority);
			List<TPipleProvince> newpps = new ArrayList<TPipleProvince>();
			for(PiplePriority pp:pList){
				TPipleProvince npp  = new TPipleProvince();
				String id = pp.getPipleId();
				int priorityx = intOf(request.getParameter("priority"+id));
				npp.setPipleId(pp.getPipleId());
				npp.setProvinceId(pp.getProvinceId());
				npp.setPriority(priorityx);
				npp.setOpStatus(pp.getOpStatus());
				newpps.add(npp);
			}
			priorityService.updatePiplePrioritys(newpps);
			response.getWriter().write("OK");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("FAIL");
		}
	}
}
