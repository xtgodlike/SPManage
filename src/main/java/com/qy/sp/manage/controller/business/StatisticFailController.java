package com.qy.sp.manage.controller.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.BasicDBObject;
import com.qy.sp.manage.common.tools.ExcelTool;
import com.qy.sp.manage.common.tools.export.ExportExcelTemplet;
import com.qy.sp.manage.common.tools.export.ReportBody;
import com.qy.sp.manage.common.utils.DateTimeUtils;
import com.qy.sp.manage.common.utils.Global;
import com.qy.sp.manage.common.utils.StringUtil;
import com.qy.sp.manage.controller.base.BaseController;
import com.qy.sp.manage.dto.StaSdkDailySearchParam;
import com.qy.sp.manage.dto.TChannel;
import com.qy.sp.manage.dto.TPiple;
import com.qy.sp.manage.dto.TProvince;
import com.qy.sp.manage.dto.TSdkApp;
import com.qy.sp.manage.dto.TStaSdkDaily;
import com.qy.sp.manage.dto.UserSession;
import com.qy.sp.manage.dto.sta.RunDailyEntity;
import com.qy.sp.manage.dto.sta.SalesDailyEntity;
import com.qy.sp.manage.dto.sta.StaSdkDailyEntity;
import com.qy.sp.manage.dto.sta.StaticEntity;
import com.qy.sp.manage.service.AppService;
import com.qy.sp.manage.service.ChannelService;
import com.qy.sp.manage.service.DicService;
import com.qy.sp.manage.service.PipleService;
import com.qy.sp.manage.service.StatisticFailService;

@Controller
@RequestMapping(value = "/statistic")
public class StatisticFailController extends BaseController {

	@Resource
	StatisticFailService statisService;
	@Resource
	private PipleService pipleService;
	@Resource
	private ChannelService channelService;
	@Resource
	private AppService appService;
	@Resource
	private DicService dicService;

	/**
	 * 跳转到运行统计页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toStatistic")
	public String toStatistic(HttpServletRequest request) {
		String pipleNumber = request.getParameter("pipleNumber");
		String pipleId = request.getParameter("pipleId");
		String channelId = request.getParameter("channelId");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		UserSession uSession = (UserSession) request.getSession().getAttribute(Global.U_SESSION);
		String userId = uSession == null ? null : uSession.getUserId();
		
		if (StringUtil.isEmptyString(startTime) && StringUtil.isEmptyString(endTime)) {
			Date today = DateTimeUtils.getCurrentTime();
			Date yesterday = DateTimeUtils.addDays(today, -1);
			startTime = DateTimeUtils.formatTime(yesterday, "yyyy-MM-dd");
			endTime = DateTimeUtils.formatTime(today, "yyyy-MM-dd");
		}

		List<TPiple> piples = pipleService.getPiplesByUserId(userId);
		List<TChannel> channels = channelService.getChannelsByUserId(userId);

		List<StaticEntity> listData = this.statisService.getRunStatisticData(userId, pipleNumber,pipleId, channelId, startTime, endTime,false);
		request.setAttribute("listData", listData);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("piples", piples);
		request.setAttribute("channels", channels);
		request.setAttribute("selectPipleNumber", pipleNumber);
		request.setAttribute("selectPipleId", pipleId);
		request.setAttribute("selectChannelId", channelId);
		return "/statistic/statisticFail";
	}
	
	/**
	 * 分省运行统计页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toStatisticForProvince")
	public String toStatisticForProvince(HttpServletRequest request) {
		String pipleNumber = request.getParameter("pipleNumber");
		String pipleId = request.getParameter("pipleId");
		String channelId = request.getParameter("channelId");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		UserSession uSession = (UserSession) request.getSession().getAttribute(Global.U_SESSION);
		String userId = uSession == null ? null : uSession.getUserId();
		
		if (StringUtil.isEmptyString(startTime) && StringUtil.isEmptyString(endTime)) {
			Date today = DateTimeUtils.getCurrentTime();
			Date yesterday = DateTimeUtils.addDays(today, -1);
			startTime = DateTimeUtils.formatTime(yesterday, "yyyy-MM-dd");
			endTime = DateTimeUtils.formatTime(today, "yyyy-MM-dd");
		}

		List<TPiple> piples = pipleService.getPiplesByUserId(userId);
		List<TChannel> channels = channelService.getChannelsByUserId(userId);

		List<StaticEntity> listData = this.statisService.getRunStatisticData(userId, pipleNumber,pipleId, channelId, startTime, endTime,true);
		request.setAttribute("listData", listData);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("piples", piples);
		request.setAttribute("channels", channels);
		request.setAttribute("selectPipleNumber", pipleNumber);
		request.setAttribute("selectPipleId", pipleId);
		request.setAttribute("selectChannelId", channelId);
		return "/statistic/statisticFailProvince";
	}

	/**
	 * 跳转到销售日报
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toSalesDaily")
	public String toSalesDaily(HttpServletRequest request){
		String pipleNumber = request.getParameter("pipleNumber");
		String pipleId = request.getParameter("pipleId");
		String channelId = request.getParameter("channelId");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		UserSession uSession = (UserSession) request.getSession().getAttribute(Global.U_SESSION);
		String userId = uSession == null ? null : uSession.getUserId();
		
		if (StringUtil.isEmptyString(startTime) && StringUtil.isEmptyString(endTime)) {
			Date today = DateTimeUtils.getCurrentTime();
			Date yesterday = DateTimeUtils.addDays(today, -1);
			startTime = DateTimeUtils.formatTime(yesterday, "yyyy-MM-dd");
			endTime = DateTimeUtils.formatTime(today, "yyyy-MM-dd");
		}
		
		List<TPiple> piples = pipleService.getPiplesByUserId(userId);
		List<TChannel> channels = channelService.getChannelsByUserId(userId);
		List<SalesDailyEntity> listData = this.statisService.getSalesDailyData(userId, pipleNumber,pipleId, channelId, startTime, endTime);
		
		Double totalAll = 0.0;
		Double totalUnfinished = 0.0;
		Double totalFail = 0.0;
		Double totalChannel = 0.0;
		Double totalSuccess = 0.0;
		Double totalDeducted = 0.0;
		for(SalesDailyEntity item : listData){
			totalAll += item.getAmountTotal();
			totalUnfinished += item.getAmountUnfinished();
			totalFail += item.getAmountFail();
			totalSuccess += item.getAmountSuccess();
			totalChannel += item.getAmountChannel();
			totalDeducted += item.getAmountDeducted();
		}
		
		request.setAttribute("totalAll", totalAll);
		request.setAttribute("totalUnfinished", totalUnfinished);
		request.setAttribute("totalFail", totalFail);
		request.setAttribute("totalSuccess", totalSuccess);
		request.setAttribute("totalChannel", totalChannel);
		request.setAttribute("totalDeducted", totalDeducted);
		
		request.setAttribute("listData", listData);	
		request.setAttribute("piples", piples);
		request.setAttribute("channels", channels);
		
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("selectPipleId", pipleId);
		request.setAttribute("selectChannelId", channelId);
		request.setAttribute("selectPipleNumber", pipleNumber);
		
		return "/statistic/salesDaily";
	}
	
	/**
	 * 当日运行统计
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toRunSameDay")
	public String toRunSameDay(HttpServletRequest request){
		String pipleNumber = request.getParameter("pipleNumber");
		String pipleId = request.getParameter("pipleId");
		String channelId = request.getParameter("channelId");
		UserSession uSession = (UserSession) request.getSession().getAttribute(Global.U_SESSION);
		String userId = uSession == null ? null : uSession.getUserId();
		
		List<TPiple> piples = pipleService.getPiplesByUserId(userId);
		List<TChannel> channels = channelService.getChannelsByUserId(userId);
		List<RunDailyEntity> listData = this.statisService.getRunDailyData(userId, pipleNumber,pipleId, channelId);

		request.setAttribute("listData", listData);	
		request.setAttribute("piples", piples);
		request.setAttribute("channels", channels);
		request.setAttribute("selectPipleId", pipleId);
		request.setAttribute("selectChannelId", channelId);
		request.setAttribute("selectPipleNumber", pipleNumber);
		
		return "/statistic/runSameDay";
	}
	
	/**
	 * SDK操作统计日报
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toStaSdkDaily")
	public String toStaSdkDaily(HttpServletRequest request) throws Exception{
		String appId = stringOf(request.getParameter("appId"));
		String channelId = stringOf(request.getParameter("channelId"));
		Integer provinceId = StringUtil.isEmptyString(request.getParameter("provinceId"))?null:Integer.valueOf(request.getParameter("provinceId"));
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
//		UserSession uSession = (UserSession) request.getSession().getAttribute(Global.U_SESSION);
		
		Date startDate = null;
		Date endDate = null;
		if (StringUtil.isEmptyString(startTime) && StringUtil.isEmptyString(endTime)) {
			Date today = DateTimeUtils.getCurrentTime();
			Date lastday = DateTimeUtils.addDays(today, +1);
			startTime = DateTimeUtils.formatTime(today, DateTimeUtils.yyyyMMdd);
			endTime = DateTimeUtils.formatTime(lastday, DateTimeUtils.yyyyMMdd);
			startDate =  DateTimeUtils.toTime(startTime, DateTimeUtils.yyyyMMdd);
			endDate = DateTimeUtils.toTime(endTime, DateTimeUtils.yyyyMMdd);
		}else{
			startDate = DateTimeUtils.StringToDate(startTime, DateTimeUtils.yyyyMMdd);
			endDate = DateTimeUtils.StringToDate(endTime, DateTimeUtils.yyyyMMdd);
		}
		StaSdkDailySearchParam sParam = new StaSdkDailySearchParam();
		sParam.setStartDate(startDate);
		sParam.setEndDate(endDate);
		sParam.setAppId(appId);
		sParam.setChannelId(channelId);
		sParam.setProvinceId(provinceId);
		
		List<TSdkApp> apps = appService.getAllApps();
		List<TChannel> channels = channelService.getAllChannels();
		List<TProvince> provinces = dicService.getAllProvinces();
		StaSdkDailyEntity staSdkde = statisService.getStaSdkDailyEntity(sParam);
		
		request.setAttribute("staSdkde", staSdkde);	
		request.setAttribute("apps", apps);
		request.setAttribute("channels", channels);
		request.setAttribute("provinces", provinces);
		request.setAttribute("param", sParam);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		
		return "/statistic/staSdkDaily";
	}
	
	/**
	 * 当日运行统计导出
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getRunDailyReport")
	public void getReportBody(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String pipleNumber = request.getParameter("pipleNumber");
		String pipleId = request.getParameter("pipleId");
		String channelId = request.getParameter("channelId");
		UserSession uSession = (UserSession) request.getSession().getAttribute(Global.U_SESSION);
		String userId = uSession == null ? null : uSession.getUserId();
		String fileName = "SP平台当日运行统计"+DateTimeUtils.formatTime(DateTimeUtils.getCurrentDate(), "yyyy-MM-dd") +".xls";
		try {
		List<RunDailyEntity> datas = this.statisService.getRunDailyData(userId,pipleNumber, pipleId, channelId);
		ReportBody billBody = this.statisService.getRunDailyBody(datas);
		ExportExcelTemplet aee = new ExportExcelTemplet();
			response.reset();
			response.setContentType("application/x-msdownload");
	         //文件名编码成UTF-8
             response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName,"UTF-8"));
			aee.exportExcel(billBody, response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		}
	}
	
	/**
	 * 销售日报导出
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getSalesDailyReport")
	public void getSalesDailyReport(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String pipleNumber = request.getParameter("pipleNumber");
		String pipleId = request.getParameter("pipleId");
		String channelId = request.getParameter("channelId");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		UserSession uSession = (UserSession) request.getSession().getAttribute(Global.U_SESSION);
		String userId = uSession == null ? null : uSession.getUserId();
		
		if (StringUtil.isEmptyString(startTime) && StringUtil.isEmptyString(endTime)) {
			Date today = DateTimeUtils.getCurrentTime();
			Date yesterday = DateTimeUtils.addDays(today, -1);
			startTime = DateTimeUtils.formatTime(yesterday, "yyyy-MM-dd");
			endTime = DateTimeUtils.formatTime(today, "yyyy-MM-dd");
		}
		
		String fileName =  "SP平台销售日报"+startTime+"-"+endTime+".xls";
		try {
			List<SalesDailyEntity> listData = this.statisService.getSalesDailyData(userId, pipleNumber,pipleId, channelId, startTime, endTime);
		ReportBody billBody = this.statisService.getSalesDailyBody(listData);
		ExportExcelTemplet aee = new ExportExcelTemplet();
			response.reset();
			response.setContentType("application/x-msdownload");
	         //文件名编码成UTF-8
             response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName,"UTF-8"));
			aee.exportExcel(billBody, response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		}
	}
	
	/**
	 * 运行统计报表导出
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toStatisticReport")
	public void toStatisticReport(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String pipleNumber = request.getParameter("pipleNumber");
		String pipleId = request.getParameter("pipleId");
		String channelId = request.getParameter("channelId");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		UserSession uSession = (UserSession) request.getSession().getAttribute(Global.U_SESSION);
		String userId = uSession == null ? null : uSession.getUserId();
		
		if (StringUtil.isEmptyString(startTime) && StringUtil.isEmptyString(endTime)) {
			Date today = DateTimeUtils.getCurrentTime();
			Date yesterday = DateTimeUtils.addDays(today, -1);
			startTime = DateTimeUtils.formatTime(yesterday, "yyyy-MM-dd");
			endTime = DateTimeUtils.formatTime(today, "yyyy-MM-dd");
		}
		
		String fileName =  "SP平台运行统计"+startTime+"-"+endTime+".xls";
		String[] title = {"交易日期","通道编号","通道名称","渠道名称","总金额","总笔数","未完成金额","未完成笔数","失败金额","失败笔数","扣量后成功金额","扣量后成功笔数","扣量金额","扣量笔数","扣量前成功率","扣量后成功率"} ;
		String[] key = {"compDateString","pipleNumber","pipleName","channelName","amountTotal","countToltal","amountW","countW","amountF","countF","amountChannel","countChannel","amountDeducted","countDeducted","successRatePreDeducted","successRateAfterDeducted"} ;
		try {
			List<StaticEntity> listData = statisService.getRunStatisticData(userId, pipleNumber,pipleId, channelId, startTime, endTime,false);
			List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
			for (StaticEntity sEntity : listData) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("compDateString", sEntity.getCompDateString());
				map.put("pipleNumber", sEntity.getPipleNumber());
				map.put("pipleName", sEntity.getPipleName());
				map.put("channelName", sEntity.getChannelName());
				map.put("amountTotal", sEntity.getAmountTotal()+"");
				map.put("countToltal", sEntity.getCountToltal()+"");
				map.put("amountW", sEntity.getAmountW()+"");
				map.put("countW", sEntity.getCountW()+"");
				map.put("amountF", sEntity.getAmountF()+"");
				map.put("countF", sEntity.getCountF()+"");
				map.put("amountChannel", sEntity.getAmountChannel()+"");
				map.put("countChannel", sEntity.getCountChannel()+"");
				map.put("amountDeducted", sEntity.getAmountDeducted()+"");
				map.put("countDeducted", sEntity.getCountDeducted()+"");
				map.put("successRatePreDeducted", sEntity.getSuccessRatePreDeducted());
				map.put("successRateAfterDeducted", sEntity.getSuccessRateAfterDeducted());
				mapList.add(map);
			}
			ExcelTool.createN(fileName, title, key, mapList, response);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		}
	}
	
	/**
	 * 分省运行统计报表导出
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toStatisticForProvinceReport")
	public void toStatisticForProvinceReport(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String pipleNumber = request.getParameter("pipleNumber");
		String pipleId = request.getParameter("pipleId");
		String channelId = request.getParameter("channelId");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		UserSession uSession = (UserSession) request.getSession().getAttribute(Global.U_SESSION);
		String userId = uSession == null ? null : uSession.getUserId();
		
		if (StringUtil.isEmptyString(startTime) && StringUtil.isEmptyString(endTime)) {
			Date today = DateTimeUtils.getCurrentTime();
			Date yesterday = DateTimeUtils.addDays(today, -1);
			startTime = DateTimeUtils.formatTime(yesterday, "yyyy-MM-dd");
			endTime = DateTimeUtils.formatTime(today, "yyyy-MM-dd");
		}
		
		String fileName =  "SP平台分省运行统计"+startTime+"-"+endTime+".xls";
		String[] title = {"交易日期","通道编号","通道名称","渠道名称","省份","总金额","总笔数","未完成金额","未完成笔数","失败金额","失败笔数","扣量后成功金额","扣量后成功笔数","扣量金额","扣量笔数","扣量前成功率","扣量后成功率"} ;
		String[] key = {"compDateString","pipleNumber","pipleName","channelName","provinceName","amountTotal","countToltal","amountW","countW","amountF","countF","amountChannel","countChannel","amountDeducted","countDeducted","successRatePreDeducted","successRateAfterDeducted"} ;
		try {
			List<StaticEntity> listData = statisService.getRunStatisticData(userId, pipleNumber,pipleId, channelId, startTime, endTime,true);
			List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
			for (StaticEntity sEntity : listData) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("compDateString", sEntity.getCompDateString());
				map.put("pipleNumber", sEntity.getPipleNumber());
				map.put("pipleName", sEntity.getPipleName());
				map.put("channelName", sEntity.getChannelName());
				map.put("provinceName", sEntity.getProvinceName());
				map.put("amountTotal", sEntity.getAmountTotal()+"");
				map.put("countToltal", sEntity.getCountToltal()+"");
				map.put("amountW", sEntity.getAmountW()+"");
				map.put("countW", sEntity.getCountW()+"");
				map.put("amountF", sEntity.getAmountF()+"");
				map.put("countF", sEntity.getCountF()+"");
				map.put("amountChannel", sEntity.getAmountChannel()+"");
				map.put("countChannel", sEntity.getCountChannel()+"");
				map.put("amountDeducted", sEntity.getAmountDeducted()+"");
				map.put("countDeducted", sEntity.getCountDeducted()+"");
				map.put("successRatePreDeducted", sEntity.getSuccessRatePreDeducted());
				map.put("successRateAfterDeducted", sEntity.getSuccessRateAfterDeducted());
				mapList.add(map);
			}
			ExcelTool.createN(fileName, title, key, mapList, response);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		}
	}
	
	
	/**
	 * SDK运行统计日报导出
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toStaSdkDailyReport")
	public void toStaSdkDailyReport(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String appId = stringOf(request.getParameter("appId"));
		String channelId = stringOf(request.getParameter("channelId"));
		Integer provinceId = StringUtil.isEmptyString(request.getParameter("provinceId"))?null:Integer.valueOf(request.getParameter("provinceId"));
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
//		UserSession uSession = (UserSession) request.getSession().getAttribute(Global.U_SESSION);
		
		Date startDate = null;
		Date endDate = null;
		if (StringUtil.isEmptyString(startTime) && StringUtil.isEmptyString(endTime)) {
			Date today = DateTimeUtils.getCurrentTime();
			Date lastday = DateTimeUtils.addDays(today, +1);
			startTime = DateTimeUtils.formatTime(today, DateTimeUtils.yyyyMMdd);
			endTime = DateTimeUtils.formatTime(lastday, DateTimeUtils.yyyyMMdd);
			startDate =  DateTimeUtils.toTime(startTime, DateTimeUtils.yyyyMMdd);
			endDate = DateTimeUtils.toTime(endTime, DateTimeUtils.yyyyMMdd);
		}else{
			startDate = DateTimeUtils.StringToDate(startTime, DateTimeUtils.yyyyMMdd);
			endDate = DateTimeUtils.StringToDate(endTime, DateTimeUtils.yyyyMMdd);
		}
		StaSdkDailySearchParam sParam = new StaSdkDailySearchParam();
		sParam.setStartDate(startDate);
		sParam.setEndDate(endDate);
		sParam.setAppId(appId);
		sParam.setChannelId(channelId);
		sParam.setProvinceId(provinceId);
		
		String fileName =  "SP平台SDK运行统计"+startTime+"-"+endTime+".xls";
		String[] title = {"统计日期","APP","渠道","省份","启动用户","新增用户","付费请求用户","付费成功用户","移动付费用户","移动付费成功率"
				,"联通付费用户","联通付费成功率","电信付费用户","电信付费成功率","信息费","付费率","付费成功率","转化率","ARPU值"} ;
		String[] key = {"compDateStr","appName","channelName","provinceName","startUserNum","addUserNum","payreqUserNum","paysucUserNum",
				"uniPuserNum","uniPaysucRatioStr","cmPuserNum","cmPaysucRatioStr","telePuserNum","telePaysucRatioStr","infoFee","payRatioStr","paySucRatioStr","translateRatioStr","arpuStr"} ;
		try {
			List<TStaSdkDaily> listData = statisService.getStaSdkDailyList(sParam);
			List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
			for (TStaSdkDaily ssd : listData) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("compDateStr", ssd.getCompDateStr());
				map.put("appName", ssd.getAppName());
				map.put("channelName", ssd.getChannelName());
				map.put("provinceName", ssd.getProvinceName()+"");
				map.put("startUserNum", ssd.getStartUserNum()+"");
				map.put("addUserNum", ssd.getAddUserNum()+"");
				map.put("payreqUserNum", ssd.getPayreqUserNum()+"");
				map.put("paysucUserNum", ssd.getPaysucUserNum()+"");
				map.put("uniPuserNum", ssd.getUniPuserNum()+"");
				map.put("uniPaysucRatioStr", ssd.getUniPaysucRatioStr());
				map.put("cmPuserNum",ssd.getCmPuserNum()+"");
				map.put("cmPaysucRatioStr", ssd.getCmPaysucRatioStr());
				map.put("telePuserNum", ssd.getTelePuserNum()+"");
				map.put("telePaysucRatioStr", ssd.getTelePaysucRatioStr());
				map.put("infoFee", ssd.getInfoFee()+"");
				map.put("payRatioStr", ssd.getPayRatioStr());
				map.put("paySucRatioStr", ssd.getPaySucRatioStr());
				map.put("translateRatioStr", ssd.getTranslateRatioStr());
				map.put("arpuStr", ssd.getArpuStr());
				mapList.add(map);
			}
			ExcelTool.createN(fileName, title, key, mapList, response);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		}
	}
	
	
}
