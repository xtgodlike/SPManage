package com.qy.sp.manage.controller.business;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qy.sp.manage.controller.base.BaseController;
import com.qy.sp.manage.dto.Page;
import com.qy.sp.manage.dto.TChannel;
import com.qy.sp.manage.dto.TPiple;
import com.qy.sp.manage.dto.TProvince;
import com.qy.sp.manage.dto.TSdkApp;
import com.qy.sp.manage.dto.TSdkconfig;
import com.qy.sp.manage.dto.TSdkconfigCross;
import com.qy.sp.manage.dto.TSdkconfigCrossKey;
import com.qy.sp.manage.service.AppService;
import com.qy.sp.manage.service.ChannelService;
import com.qy.sp.manage.service.DicService;
import com.qy.sp.manage.service.PipleService;
import com.qy.sp.manage.service.SdkConfigService;

@Controller
@RequestMapping(value = "/sdk")
public class SdkConfigController extends BaseController{
	
	@Resource
	private SdkConfigService sdkConfigService;
	@Resource
	private ChannelService channelService;
	@Resource
	private AppService appService;
	@Resource
	private DicService dicService;
	@Resource
	private PipleService pipleService;

	@RequestMapping(value = "/toConfigList.do")
	public String toConfigList(HttpServletRequest request) throws Exception{
		String pageNumber = request.getParameter("pageNumber")==null?"1":request.getParameter("pageNumber");
		String configId = stringOf(request.getParameter("sdkconfigId"));
		String configDescription = stringOf(request.getParameter("configDescription"));
		TSdkconfig sdkconfig = new TSdkconfig();
		sdkconfig.setConfigId(configId);
		sdkconfig.setConfigDescription(configDescription);
		
		int items = sdkConfigService.getSdkConfigItems(sdkconfig);
		Page page = new Page();
		page.setPageAllCount(items);
		page.setPageNumber(Integer.parseInt(pageNumber));
		List<TSdkconfig> sdkconfigs = sdkConfigService.getSdkConfigList(sdkconfig, page);
		
		request.setAttribute("page", page);
		request.setAttribute("sdkconfig", sdkconfig);
		request.setAttribute("sdkconfigs", sdkconfigs);
		
		return "/sdkConfig/listSdkConfig";
	}
	
	@RequestMapping(value = "/toConfigCrossList.do")
	public String toConfigCrossList(HttpServletRequest request) throws Exception{
		String pageNumber = request.getParameter("pageNumber")==null?"1":request.getParameter("pageNumber");
		String configId = stringOf(request.getParameter("configId"));
		String appId = stringOf(request.getParameter("appId"));
		String channelId = stringOf(request.getParameter("channelId"));
		String provinceId = stringOf(request.getParameter("provinceId"));
		String pipleId = stringOf(request.getParameter("pipleId"));
		TSdkconfigCross sdkconfigCross = new TSdkconfigCross();
		sdkconfigCross.setConfigId(configId);
		sdkconfigCross.setAppId(appId);
		sdkconfigCross.setChannelId(channelId);
		sdkconfigCross.setProvinceId(provinceId);
		sdkconfigCross.setPipleId(pipleId);
		int items = sdkConfigService.getSdkConfigCrossItems(sdkconfigCross);
		Page page = new Page();
		page.setPageAllCount(items);
		page.setPageNumber(Integer.parseInt(pageNumber));
		List<TSdkconfigCross> sdkcss = sdkConfigService.getSdkConfigCrossList(sdkconfigCross, page);
		TSdkconfig sdkconfig = sdkConfigService.getSdkConfig(configId);
		List<TSdkApp> apps = appService.getAllApps();
		List<TChannel> channels = channelService.getAllChannels();
		List<TProvince> provinces = dicService.getAllProvinces();
		List<TPiple>  piples = pipleService.getAllPiples();
		request.setAttribute("configId", configId);
		request.setAttribute("page", page);
		request.setAttribute("sdkconfig", sdkconfig);
		request.setAttribute("sdkconfigCross", sdkconfigCross);
		request.setAttribute("sdkcss", sdkcss);
		request.setAttribute("apps", apps);
		request.setAttribute("channels", channels);
		request.setAttribute("provinces", provinces);
		request.setAttribute("piples", piples);
		
		return "/sdkConfig/listSdkConfigCross";
	}
	
	@RequestMapping(value = "/toUpdateSdkConfig.do")
	public String toUpdateSdkConfig(HttpServletRequest request)  throws Exception{
		String configId = stringOf(request.getParameter("configId"));
		TSdkconfig sdkConfig = sdkConfigService.getSdkConfig(configId);
		request.setAttribute("sdkConfig", sdkConfig);
		return "/sdkConfig/updateSdkConfig";
	}
	
	@RequestMapping(value = "/verifySdkConfigId.do")
	public void verifySdkConfigId(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String configId = stringOf(new String(request.getParameter("configId").getBytes("iso-8859-1"),"utf-8"));
		TSdkconfig sdkconfig = sdkConfigService.getSdkConfig(configId);
		if(sdkconfig!=null){
			response.getWriter().write("FAIL");
		}else{
			response.getWriter().write("OK");
		}
	}
	
	@RequestMapping(value = "/doUpdateSdkConfig.do")
	public String doUpdateSdkConfig(HttpServletRequest request) throws Exception{
		try {
			String configId = stringOf(request.getParameter("configId"));
			String configDescription = stringOf(request.getParameter("configDescription"));
			String globalConfigValue = stringOf(request.getParameter("globalConfigValue"));
			TSdkconfig sdkconfig = new TSdkconfig();
			sdkconfig.setConfigId(configId);
			sdkconfig.setConfigDescription(configDescription);
			sdkconfig.setGlobalConfigValue(globalConfigValue);
			sdkConfigService.updateSdkConfig(sdkconfig);
			return "redirect:/sdk/toConfigList.do";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/doDeleteSdkConfig.do")
	public String doDeleteSdkConfig(HttpServletRequest request) throws Exception{
		try {
			String configId = stringOf(request.getParameter("configId"));
			sdkConfigService.delSdkConfig(configId);
			request.setAttribute("configId", configId);
			return "redirect:/sdk/toConfigList.do";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/toUpdateSdkConfigCross.do")
	public String toUpdateSdkConfigCross(HttpServletRequest request)  throws Exception{
		String configId = stringOf(request.getParameter("configId"));
		String appId = stringOf(request.getParameter("appId"));
		String channelId = stringOf(request.getParameter("channelId"));
		String provinceId = stringOf(request.getParameter("provinceId"));
		String pipleId = stringOf(request.getParameter("pipleId"));
		TSdkconfigCrossKey key = new TSdkconfigCrossKey();
		key.setConfigId(configId);
		key.setAppId(appId);
		key.setChannelId(channelId);
		key.setProvinceId(provinceId);
		key.setPipleId(pipleId);
		TSdkconfigCross scc = sdkConfigService.geTSdkconfigCross(key);
		request.setAttribute("scc", scc);
		List<TSdkApp> apps = appService.getAllApps();
		List<TChannel> channels = channelService.getAllChannels();
		List<TProvince> provinces = dicService.getAllProvinces();
		List<TPiple>  piples = pipleService.getAllPiples();
		request.setAttribute("configId", configId);
		request.setAttribute("scc", scc);
		request.setAttribute("apps", apps);
		request.setAttribute("channels", channels);
		request.setAttribute("provinces", provinces);
		request.setAttribute("piples", piples);
		return "/sdkConfig/updateSdkConfigCross";
	}
	
	@RequestMapping(value = "/doUpdateSdkConfigCross.do")
	public void doUpdateSdkConfigCross(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			String configId = stringOf(request.getParameter("configId"));
			String appId = stringOf(request.getParameter("appId"));
			String channelId = stringOf(request.getParameter("channelId"));
			String provinceId = stringOf(request.getParameter("provinceId"));
			String pipleId = stringOf(request.getParameter("pipleId"));
			String configValue = stringOf(request.getParameter("configValue"));
			TSdkconfigCross sdkconfigCross = new TSdkconfigCross();
			sdkconfigCross.setConfigId(configId);
			sdkconfigCross.setAppId(appId);
			sdkconfigCross.setChannelId(channelId);
			sdkconfigCross.setProvinceId(provinceId);
			sdkconfigCross.setPipleId(pipleId);
			sdkconfigCross.setConfigValue(configValue);
			String returnMsg = sdkConfigService.updateSdkConfigCross(sdkconfigCross);
			response.getWriter().write(returnMsg);
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("FAIL");
		}
	}
	
	@RequestMapping(value = "/doDeleteSdkConfigCross.do")
	public String doDeleteSdkConfigCross(HttpServletRequest request) throws Exception{
		String configId = stringOf(request.getParameter("configId"));
		String appId = stringOf(request.getParameter("appId"));
		String channelId = stringOf(request.getParameter("channelId"));
		String provinceId = stringOf(request.getParameter("provinceId"));
		String pipleId = stringOf(request.getParameter("pipleId"));
		TSdkconfigCrossKey key = new TSdkconfigCrossKey();
		key.setConfigId(configId);
		key.setAppId(appId);
		key.setChannelId(channelId);
		key.setProvinceId(provinceId);
		key.setPipleId(pipleId);
		sdkConfigService.deleteSdkConfigCross(key);
//		return "/sdkConfig/listSdkConfigCross";
//		return "redirect:/sdk/toConfigCrossList.do?configId="+configId+"&appId="+appId+"&channelId="+channelId+"&provinceId="+provinceId+"&pipleId="+pipleId;
		return "redirect:/sdk/toConfigCrossList.do?configId="+configId;
	}
	
	@RequestMapping(value = "/toUpdateSdkConfigPhone.do")
	public String toUpdateSdkConfigPhone(HttpServletRequest request)  throws Exception{
		String configId = stringOf(request.getParameter("configId"));
		TSdkconfig sdkConfig = sdkConfigService.getSdkConfig(configId);
		request.setAttribute("configId", configId);
		request.setAttribute("sdkConfig", sdkConfig);
		return "/sdkConfig/updateSdkConfigPhone";
	}
	
	@RequestMapping(value = "/doUpdateSdkConfigPhone.do")
	public void doUpdateSdkConfigPhone(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			String configId = stringOf(request.getParameter("configId"));
			String  imeis = stringOf(request.getParameter("imeis"));
			String configValue = stringOf(request.getParameter("configValue"));
			String[] imeix = imeis.split(",");
			List<String> imeiList = Arrays.asList(imeix);
			sdkConfigService.updateSdkConfigPhones(imeiList, configId, configValue);
			response.getWriter().write("OK");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/doDelSdkConfigPhone.do")
	public void doDelSdkConfigPhone(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			String configId = stringOf(request.getParameter("configId"));
			String  imeis = stringOf(request.getParameter("imeis"));
			String[] imeix = imeis.split(",");
			List<String> imeiList = Arrays.asList(imeix);
			sdkConfigService.delSdkConfigPhones(imeiList, configId);
			response.getWriter().write("OK");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write(e.getMessage());
		}
	}
}
