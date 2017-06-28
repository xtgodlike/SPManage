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

import com.qy.sp.manage.common.tools.ExcelTool;
import com.qy.sp.manage.common.utils.DateTimeUtils;
import com.qy.sp.manage.common.utils.Global;
import com.qy.sp.manage.common.utils.StringUtil;
import com.qy.sp.manage.controller.base.BaseController;
import com.qy.sp.manage.dto.Page;
import com.qy.sp.manage.dto.TChannel;
import com.qy.sp.manage.dto.TOrder;
import com.qy.sp.manage.dto.TOrderExt;
import com.qy.sp.manage.dto.TPiple;
import com.qy.sp.manage.dto.TProvince;
import com.qy.sp.manage.dto.TSdkOperation;
import com.qy.sp.manage.dto.UserSession;
import com.qy.sp.manage.dto.sta.StaticEntity;
import com.qy.sp.manage.service.ChannelService;
import com.qy.sp.manage.service.ControlService;
import com.qy.sp.manage.service.DicService;
import com.qy.sp.manage.service.PipleService;

@Controller
@RequestMapping(value = "/control")
public class ControlController extends BaseController{
	
	@Resource
	private ControlService controlService;
	@Resource
	private PipleService pipleService;
	@Resource
	private ChannelService channelService;
	@Resource
	private DicService dicService;
	
	/**
	 * 查询订单列表
	 * */
	@RequestMapping(value = "/toOrderList.do")
	public String toOrderList(HttpServletRequest request) throws Exception{
		String pageNumber = request.getParameter("pageNumber")==null?"1":request.getParameter("pageNumber");
		String pipleId = stringOf(request.getParameter("pipleId"));
		String channelId = stringOf(request.getParameter("channelId"));
		String mobile = stringOf(request.getParameter("mobile"));
		String imsi = stringOf(request.getParameter("imsi"));
		String orderId = stringOf(request.getParameter("orderId"));
		String pipleOrderId = stringOf(request.getParameter("pipleOrderId"));
		String startTime = stringOf(request.getParameter("startTime"));
		String endTime = stringOf(request.getParameter("endTime"));
		Date sDate = null;
		Date eDate = null;
		Integer orderStatus = isEmpty(request.getParameter("orderStatus"))?null:Integer.valueOf(request.getParameter("orderStatus"));
		Integer subStatus = isEmpty(request.getParameter("subStatus"))?null:Integer.valueOf(request.getParameter("subStatus"));
		Integer decStatus = isEmpty(request.getParameter("decStatus"))?null:Integer.valueOf(request.getParameter("decStatus"));
		Integer provinceId = isEmpty(request.getParameter("provinceId"))?null:Integer.valueOf(request.getParameter("provinceId"));
		if (StringUtil.isEmptyString(startTime) && StringUtil.isEmptyString(endTime)) {
			Date today = DateTimeUtils.getCurrentTime();
			Date lastday = DateTimeUtils.addDays(today, +1);
			startTime = DateTimeUtils.formatTime(today, "yyyy-MM-dd");
			endTime = DateTimeUtils.formatTime(lastday, "yyyy-MM-dd");
		}
		if(!StringUtil.isEmptyString(startTime)){
			sDate = DateTimeUtils.StringToDate(startTime, "yyyy-MM-dd");
		}
		if(!StringUtil.isEmptyString(endTime)){
			eDate = DateTimeUtils.StringToDate(endTime, "yyyy-MM-dd");
		}
		List<TOrder> orders = null;
		TOrder order = new TOrder();
		order.setOrderId(orderId);
		order.setPipleOrderId(pipleOrderId);
		order.setMobile(mobile);
		order.setImsi(imsi);
		order.setPipleId(pipleId);
		order.setChannelId(channelId);
		order.setStartDate(sDate);
		order.setEndDate(eDate);
		order.setProvinceId(provinceId);
		order.setOrderStatus(orderStatus);
		order.setSubStatus(subStatus);
		order.setDecStatus(decStatus);
		order.setStartTime(startTime);
		order.setEndTime(endTime);
		UserSession uSession = (UserSession) request.getSession().getAttribute(Global.U_SESSION);
		Page page = new Page();
		page.setPageNumber(Integer.parseInt(pageNumber));
		if(!isEmpty(mobile) || !isEmpty(imsi) || !isEmpty(orderId)  || !isEmpty(pipleOrderId)
				 || !isEmpty(pipleId)  || !isEmpty(channelId) ){
			int items = controlService.getOrderItems(order,uSession.getUserId());
			page.setPageAllCount(items);
			orders = controlService.getOrderList(order,page,uSession.getUserId());
		}
		
		List<TPiple> piples = pipleService.getPiplesByUserId(uSession.getUserId());
		List<TChannel> channels = channelService.getChannelsByUserId(uSession.getUserId());
		List<TProvince> provinces = dicService.getAllProvinces();
		request.setAttribute("mobile", mobile);
		request.setAttribute("imsi", imsi);
		request.setAttribute("orderId", orderId);
		request.setAttribute("pipleOrderId", pipleOrderId);
		request.setAttribute("orders", orders);
		request.setAttribute("order", order);
		request.setAttribute("piples", piples);
		request.setAttribute("channels", channels);
		request.setAttribute("provinces", provinces);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("page", page);
		return "/control/list_order";
	}
	
	
	@RequestMapping(value = "/getOrderInfo.do")
	public String getOrderInfo(HttpServletRequest request) throws Exception{
		String orderId = stringOf(request.getParameter("orderId"));
		TOrder order = controlService.getOrderInfo(orderId);
		List<TOrderExt> orderExts = null;
		List<TSdkOperation> serverOperations = null;
		List<TSdkOperation> sdkOperations = null;
		if(order!=null){
			orderExts = controlService.getOrderExtList(order.getOrderId());
			String groupId = order.getOrderGroupId();
			String flowId = order.getFlowId();
			if(!StringUtil.isEmptyString(groupId)){
				serverOperations = controlService.getOperatesByFlowId(groupId);
			}
			if(!StringUtil.isEmptyString(flowId)){
				sdkOperations = controlService.getOperatesByFlowId(flowId);
			}
		}
		request.setAttribute("order", order);
		request.setAttribute("orderExts", orderExts);
		request.setAttribute("serverOperations", serverOperations);
		request.setAttribute("sdkOperations", sdkOperations);
		return "/control/orderInfo";
	}
	
	/**
	 * 订单查询列表导出
	 * */
	@RequestMapping(value = "/getOrderInfoReport.do")
	public void getOrderInfoReport(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String pipleId = stringOf(request.getParameter("pipleId"));
		String channelId = stringOf(request.getParameter("channelId"));
		String mobile = stringOf(request.getParameter("mobile"));
		String imsi = stringOf(request.getParameter("imsi"));
		String orderId = stringOf(request.getParameter("orderId"));
		String pipleOrderId = stringOf(request.getParameter("pipleOrderId"));
		String startTime = stringOf(request.getParameter("startTime"));
		String endTime = stringOf(request.getParameter("endTime"));
		Date sDate = null;
		Date eDate = null;
		Integer orderStatus = isEmpty(request.getParameter("orderStatus"))?null:Integer.valueOf(request.getParameter("orderStatus"));
		Integer subStatus = isEmpty(request.getParameter("subStatus"))?null:Integer.valueOf(request.getParameter("subStatus"));
		Integer decStatus = isEmpty(request.getParameter("decStatus"))?null:Integer.valueOf(request.getParameter("decStatus"));
		Integer provinceId = isEmpty(request.getParameter("provinceId"))?null:Integer.valueOf(request.getParameter("provinceId"));
		if (StringUtil.isEmptyString(startTime) && StringUtil.isEmptyString(endTime)) {
			Date today = DateTimeUtils.getCurrentTime();
			Date lastday = DateTimeUtils.addDays(today, +1);
			startTime = DateTimeUtils.formatTime(today, "yyyy-MM-dd");
			endTime = DateTimeUtils.formatTime(lastday, "yyyy-MM-dd");
		}
		if(!StringUtil.isEmptyString(startTime)){
			sDate = DateTimeUtils.StringToDate(startTime, "yyyy-MM-dd");
		}
		if(!StringUtil.isEmptyString(endTime)){
			eDate = DateTimeUtils.StringToDate(endTime, "yyyy-MM-dd");
		}
		List<TOrder> orders = null;
		TOrder order = new TOrder();
		order.setOrderId(orderId);
		order.setPipleOrderId(pipleOrderId);
		order.setMobile(mobile);
		order.setImsi(imsi);
		order.setPipleId(pipleId);
		order.setChannelId(channelId);
		order.setStartDate(sDate);
		order.setEndDate(eDate);
		order.setProvinceId(provinceId);
		order.setOrderStatus(orderStatus);
		order.setSubStatus(subStatus);
		order.setDecStatus(decStatus);
		order.setStartTime(startTime);
		order.setEndTime(endTime);
		UserSession uSession = (UserSession) request.getSession().getAttribute(Global.U_SESSION);
		
		try {
			if(!isEmpty(mobile) || !isEmpty(imsi) || !isEmpty(orderId)  || !isEmpty(pipleOrderId)
					 || !isEmpty(pipleId)  || !isEmpty(channelId) ){
				orders = controlService.getAllOrderList(order,uSession.getUserId());
			}
			String fileName =  "订单查询列表"+startTime+"-"+endTime+".xls";
			String[] title = {"订单号","通道订单号","所属通道","所属渠道","所属省份","手机号码","IMSI","金额","返回码","订单状态","流程状态","扣量状态","请求时间","完成时间"} ;
			String[] key = {"orderId","pipleOrderId","pipleName","channelName","provinceName","mobile","imsi","amount","resultCode","orderStatusDesc","subStatusDesc","decStatusDesc","createTimeString","completeTimeString"} ;
			List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
			for (TOrder  o: orders) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("orderId", o.getOrderId());
				map.put("pipleOrderId", StringUtil.isEmptyString(o.getPipleOrderId())?"无":o.getPipleOrderId());
				map.put("pipleName", o.getPipleName());
				map.put("channelName", o.getChannelName());
				map.put("provinceName", o.getProvinceName());
				map.put("mobile", o.getMobile());
				map.put("imsi", StringUtil.isEmptyString(o.getImsi())?"无":o.getImsi());
				map.put("amount", o.getAmount()+"");
				map.put("resultCode", StringUtil.isEmptyString(o.getResultCode())?"无":o.getResultCode());
				map.put("orderStatusDesc", Global.OrderStatusDesc.message.get(o.getOrderStatus()));
				map.put("subStatusDesc", Global.SubStatusDesc.message.get(o.getSubStatus()));
				map.put("decStatusDesc", Global.ShiftStatusDesc.message.get(o.getDecStatus()));
				map.put("createTimeString", DateTimeUtils.formatTime(o.getCreateTime(), DateTimeUtils.yyyyMMddHHmmss));
				map.put("completeTimeString", o.getCompleteTime()==null?"无":DateTimeUtils.formatTime(o.getCompleteTime(), DateTimeUtils.yyyyMMddHHmmss));
				mapList.add(map);
			}
			ExcelTool.createN(fileName, title, key, mapList, response);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		}
	}
	
}
