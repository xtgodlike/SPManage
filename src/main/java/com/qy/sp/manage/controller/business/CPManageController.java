package com.qy.sp.manage.controller.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qy.sp.manage.common.utils.Global;
import com.qy.sp.manage.controller.base.BaseController;
import com.qy.sp.manage.dto.TChannel;
import com.qy.sp.manage.dto.TChannelPiple;
import com.qy.sp.manage.dto.TChannelPipleKey;
import com.qy.sp.manage.dto.TChannelProvinceLimit;
import com.qy.sp.manage.dto.TChannelProvinceLimitKey;
import com.qy.sp.manage.dto.TPiple;
import com.qy.sp.manage.dto.TPipleProvince;
import com.qy.sp.manage.dto.TSdkApp;
import com.qy.sp.manage.dto.TSdkconfig;
import com.qy.sp.manage.dto.TSdkconfigCross;
import com.qy.sp.manage.dto.TSdkconfigCrossKey;
import com.qy.sp.manage.dto.TSdkconfigMobileBase;
import com.qy.sp.manage.dto.UserSession;
import com.qy.sp.manage.service.ChannelService;
import com.qy.sp.manage.service.CpService;
import com.qy.sp.manage.service.PipleService;
import com.qy.sp.manage.service.SdkConfigService;
import com.qy.sp.manage.service.StatisticFailService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/cpmanage")
public class CPManageController extends BaseController {

	@Resource
	StatisticFailService statisService;
	@Resource
	private PipleService pipleService;
	@Resource
	private ChannelService channelService;
	@Resource
	private CpService cpService;
	@Resource
	private SdkConfigService sdkConfigService;
	private static Map<String,String> configsMap = new ConcurrentHashMap<String,String>();
	static{
		configsMap.put("codeStartTime", "codeStartTime");
		configsMap.put("isBWhite", "isBWhite");
	}
	/**
	 * 跳转到销售日报
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/cpChannelPipleManage")
	public String cpChannelPipleManage(){
		return "/cpmanage/cpChannelPipleManage";
	}
	@RequestMapping(value = "/listPiples",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String toListChannelPiples(HttpServletRequest request) throws Exception{
		JSONObject result = new JSONObject();
		List<JSONObject> data = new ArrayList<JSONObject>();
		UserSession uSession = (UserSession) request.getSession().getAttribute(Global.U_SESSION);
		String userId = uSession == null ? null : uSession.getUserId();
		List<TPiple> piples = pipleService.getPiplesByUserId(userId);
		List<TChannel> channels = channelService.getChannelsByUserId(userId);
		for(TPiple p : piples){
			for(TChannel c: channels){
				TChannelPipleKey key = new TChannelPipleKey();
				key.setChannelId(c.getChannelId());
				key.setPipleId(p.getPipleId());
				TChannelPiple channelPiple = pipleService.getChannelPipleByKey(key);
				if(channelPiple == null)
					 continue;
				JSONObject pipleChannelObject = new JSONObject();
				pipleChannelObject.put("pipleId", p.getPipleId());
				pipleChannelObject.put("pipleName",p.getPipleName());
				pipleChannelObject.put("channelId",c.getChannelId());
				pipleChannelObject.put("tradeDay",channelPiple.getTradeDay());
				pipleChannelObject.put("tradeMonth",channelPiple.getTradeMonth());
				pipleChannelObject.put("notifyUrl",channelPiple.getNotifyUrl());
				pipleChannelObject.put("volt",channelPiple.getVolt());
				pipleChannelObject.put("opStatus",channelPiple.getOpStatus());
				pipleChannelObject.put("settlementNum",channelPiple.getSettlementRatio()*100);
				data.add(pipleChannelObject);
			}
		}
		result.put("rows", data);
		result.put("total", data.size());
		return result.toString();
	}
	@RequestMapping(value = "/toChannelProvinceLimit")
	public String toChannelProvinceLimit(HttpServletRequest request) throws Exception{
		String pipleId = request.getParameter("pipleId");
		String channelId = request.getParameter("channelId");
		List<TPipleProvince> pipleProvinces = pipleService.getPipleProvinceList(pipleId);  
		TChannelProvinceLimitKey key = new TChannelProvinceLimitKey();
		key.setPipleId(pipleId);
		key.setChannelId(channelId);
		List<TChannelProvinceLimit> cpls = pipleService.getListByPipleIdAndChannelId(key);
		TPiple piple = pipleService.getPipleByPipleId(pipleId);
		TChannel channel = channelService.getChannelById(channelId);
		request.setAttribute("piple", piple);
		request.setAttribute("channel", channel);
		request.setAttribute("pipleProvinces", pipleProvinces);
		request.setAttribute("cpls", cpls);
		return "/cpmanage/updateChannelProvinceLimit";
	}
	
	@RequestMapping(value = "/cpSdkConfigManage")
	public String cpSdkConfigManage(){
		return "/cpmanage/cpSdkConfigManage";
	}
	@RequestMapping(value = "/listSdkConfigs",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String toListSdkConfigs(HttpServletRequest request) throws Exception{
		JSONObject result = new JSONObject();
		List<JSONObject> data = new ArrayList<JSONObject>();
		UserSession uSession = (UserSession) request.getSession().getAttribute(Global.U_SESSION);
		String userId = uSession == null ? null : uSession.getUserId();
		List<TPiple> piples = pipleService.getPiplesByUserId(userId);
		List<TChannel> channels = channelService.getChannelsByUserId(userId);
		List<TSdkApp> apps = cpService.getUserApps(userId);
		for(TChannel c: channels){
			for(TPiple p : piples){
				for(TSdkApp app : apps){
					TSdkconfigCrossKey key = new TSdkconfigCrossKey();
					key.setAppId(app.getAppId());
					key.setChannelId(c.getChannelId());
					key.setPipleId(p.getPipleId());
					List<TSdkconfigCross> sdkCrosses = sdkConfigService.geTSdkconfigChannelAppPiple(key);
					if(sdkCrosses == null || sdkCrosses.size() <=0)
						continue;
					for(TSdkconfigCross sdkCross :sdkCrosses){
						if(configsMap.containsKey(sdkCross.getConfigId())){
							JSONObject configObject = new JSONObject();
							configObject.put("pipleId", p.getPipleId());
							configObject.put("pipleName",p.getPipleName());
							configObject.put("channelId",c.getChannelId());
							configObject.put("appId",app.getAppId());
							configObject.put("appName",app.getAppName());
							configObject.put("configId",sdkCross.getConfigId());
							TSdkconfig config = sdkConfigService.getSdkConfig(sdkCross.getConfigId());
							configObject.put("configDescription",config.getConfigDescription());
							configObject.put("configValue",sdkCross.getConfigValue());
							data.add(configObject);
						}
					}
				}
				
			}
		}
		result.put("rows", data);
		result.put("total", data.size());
		return result.toString();
	}
	@RequestMapping(value = "/cpMobileBaseSdkConfigManage")
	public String cpMobileBaseSdkConfigManage(){
		return "/cpmanage/cpMobileBaseSdkConfigManage";
	}
	@RequestMapping(value = "/listMobileBaseSdkConfigs",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String toListMobileBaseSdkConfigs(HttpServletRequest request) throws Exception{
		JSONObject result = new JSONObject();
		List<JSONObject> data = new ArrayList<JSONObject>();
		UserSession uSession = (UserSession) request.getSession().getAttribute(Global.U_SESSION);
		String userId = uSession == null ? null : uSession.getUserId();
		List<TSdkApp> apps = cpService.getUserApps(userId);
		for(TSdkApp app: apps){
			List<TSdkconfigMobileBase> list = sdkConfigService.getMobileBaseSDkConfig(app.getAppId());
			for(TSdkconfigMobileBase config: list){
				data.add(JSONObject.fromObject(config));
			}
		}
		result.put("rows", data);
		result.put("total", data.size());
		return result.toString();
	}
	@RequestMapping(value = "/doUpdateMobileBaseSdkConfig",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public void doUpdateMobileBaseSdkConfig(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String appId = request.getParameter("appId");
			String contentId = request.getParameter("contentId");
			String releaseChannelId = request.getParameter("releaseChannelId");
			String cpId = request.getParameter("cpId");
			String startCodeTime = request.getParameter("startCodeTime");
			String isUseBWhite = request.getParameter("isUseBWhite");
			TSdkconfigMobileBase tSdkconfigMobileBase = new TSdkconfigMobileBase();
			tSdkconfigMobileBase.setAppId(appId);
			tSdkconfigMobileBase.setContentId(contentId);
			tSdkconfigMobileBase.setCpId(cpId);
			tSdkconfigMobileBase.setReleaseChannelId(releaseChannelId);
			tSdkconfigMobileBase.setStartCodeTime(startCodeTime);
			tSdkconfigMobileBase.setIsUseBWhite(isUseBWhite);
			String result =  sdkConfigService.updateMobileBaseSDkConfig(tSdkconfigMobileBase);
			response.getWriter().write(result);
	}
}
