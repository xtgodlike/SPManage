package com.qy.sp.manage.controller.business;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qy.sp.manage.common.utils.FileUtils;
import com.qy.sp.manage.common.utils.Global;
import com.qy.sp.manage.common.utils.KeyHelper;
import com.qy.sp.manage.common.utils.StringUtil;
import com.qy.sp.manage.controller.base.BaseController;
import com.qy.sp.manage.dto.Page;
import com.qy.sp.manage.dto.TBlobContent;
import com.qy.sp.manage.dto.TChannel;
import com.qy.sp.manage.dto.TDic;
import com.qy.sp.manage.dto.TPiple;
import com.qy.sp.manage.dto.TProvince;
import com.qy.sp.manage.dto.TSdkApp;
import com.qy.sp.manage.dto.TSdkconfig;
import com.qy.sp.manage.dto.TSdktask;
import com.qy.sp.manage.dto.TSdktaskCross;
import com.qy.sp.manage.dto.TSdktaskCrossKey;
import com.qy.sp.manage.dto.TSdktaskGlobal;
import com.qy.sp.manage.dto.TSdktaskGlobalKey;
import com.qy.sp.manage.service.AppService;
import com.qy.sp.manage.service.ChannelService;
import com.qy.sp.manage.service.DicService;
import com.qy.sp.manage.service.SdkTaskService;

@Controller
@RequestMapping(value = "/sdk")
public class SdkTaskController extends BaseController{
	
	private Logger log = Logger.getLogger(SdkTaskController.class);
	@Resource
	private SdkTaskService sdkTaskService;
	@Resource
	private AppService appService;
	@Resource
	private ChannelService channelService;
	@Resource
	private DicService dicService;
	
	@RequestMapping(value = "/toTaskList.do")
	public String toConfigList(HttpServletRequest request) throws Exception{
		String pageNumber = request.getParameter("pageNumber")==null?"1":request.getParameter("pageNumber");
		String taskName = stringOf(request.getParameter("taskName"));
		Integer taskStatus = isEmpty(request.getParameter("taskStatus"))?null:Integer.valueOf(request.getParameter("taskStatus"));
		TSdktask sdktask = new TSdktask();
		sdktask.setTaskName(taskName);
		sdktask.setTaskStatus(taskStatus);
		int items = sdkTaskService.getSdkTaskItems(sdktask);
		Page page = new Page();
		page.setPageAllCount(items);
		page.setPageNumber(Integer.parseInt(pageNumber));
		List<TSdktask> sdktasks = sdkTaskService.getSdkTaskList(sdktask, page);
		request.setAttribute("page", page);
		request.setAttribute("sdktask", sdktask);
		request.setAttribute("sdktasks", sdktasks);
		return "/sdkTask/listSdkTask";
	}
	

	@RequestMapping(value = "/toUpdateSdkTask.do")
	public String toUpdateSdkTask(HttpServletRequest request)  throws Exception{
		String taskId = stringOf(request.getParameter("taskId"));
		TSdktask sdktask = sdkTaskService.getSdkTask(taskId);
		request.setAttribute("taskId", taskId);
		request.setAttribute("sdktask", sdktask);
		return "/sdkTask/updateSdkTask";
	}
	
	@RequestMapping(value = "/verifySdkTaskName.do")
	public void verifySdkTaskName(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String taskName = stringOf(new String(request.getParameter("taskName").getBytes("iso-8859-1"),"utf-8"));
		String taskId = stringOf(request.getParameter("taskId"));
		if(!"".equals(taskId) && taskId!=null){
			TSdktask sdktask = sdkTaskService.getSdkTask(taskId);
			if(taskName.equals(sdktask.getTaskName())){
				response.getWriter().write("OK");
				return;
			}
		}
		TSdktask sdktask = sdkTaskService.getSdkTaskByName(taskName);
		if(sdktask!=null){
			response.getWriter().write("FAIL");
		}else{
			response.getWriter().write("OK");
		}
	}
	
	/**
	 * 验证任务ID是否已经存在
	 * @throws Exception
	 */
	@RequestMapping(value = "/verifyTaskId.do")
	public void verifyTaskId(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String taskId = request.getParameter("taskId");
		String isOper = request.getParameter("isOper");
		TSdktask task = sdkTaskService.getSdkTask(taskId);
		if(task!=null && StringUtil.isEmptyString(isOper)){
			response.getWriter().write("FAIL");
		}else{
			response.getWriter().write("OK");
		}
	}
	
	@RequestMapping(value = "/doUpdateSdkTask.do")
	public String doUpdatePiple(HttpServletRequest request) throws Exception{
		String taskId = null;
		String taskName = null;
		String taskDesc = null;
		int taskStatus = 0;
		String taskPlugin = null;
		String pluginf = null;
		String taskVersion = null; 
		TBlobContent bContent = null;
	 try {
		  request.setCharacterEncoding("UTF-8");
		  DiskFileItemFactory factory = new DiskFileItemFactory();
		  ServletFileUpload upload = new ServletFileUpload(factory);
		   List items = upload.parseRequest(request);
		   Iterator itr = items.iterator();
		   while (itr.hasNext()) {
			    FileItem item = (FileItem) itr.next();
			    if (item.isFormField()) {
			    	 String fieldName = item.getFieldName();
			    	 String fieldValue = item.getString("UTF-8");
				     log.info("表单参数名:" +fieldName+ "，表单参数值:" +fieldValue );
				     if(fieldName.equals("taskId")) taskId = stringOf(fieldValue);
				     if(fieldName.equals("taskName")) taskName = stringOf(fieldValue);
				     if(fieldName.equals("taskDesc")) taskDesc = stringOf(fieldValue);
				     if(fieldName.equals("taskStatus")) taskStatus = intOf(fieldValue);
				     if(fieldName.equals("taskVersion")) taskVersion = stringOf(fieldValue);
				     if(fieldName.equals("taskPlugin")) taskPlugin = stringOf(fieldValue);
			    } else {
			    	  log.info("上传文件的大小:" + item.getSize());
			    	  log.info("上传文件的类型:" + item.getContentType());
			    	  log.info("上传文件的名称:" + item.getName());
				      String fileName = item.getName(); // 文件名
				      String fieldName = item.getFieldName(); // 参数名
				      InputStream fileStr = item.getInputStream();
				      byte[] fileByte = FileUtils.InputStreamToByte(fileStr);
				       if(fieldName.equals("pluginf") && fileByte.length!=0){ // 任务插件
				    	bContent = new TBlobContent();
				    	taskPlugin = KeyHelper.createKey();
				    	bContent.setFileId(taskPlugin);
				    	bContent.setFilename(fileName);
				    	bContent.setFileContent(fileByte);
				      }
			    }
		    }
		   TSdktask sdktask = null;
		   if(taskId!=null && !"".equals(taskId)){
			   sdktask = sdkTaskService.getSdkTask(taskId);
			   if(sdktask==null) { // 则taskId为指定ID
				   sdktask = new TSdktask();
				   sdktask.setTaskId(taskId);
			   }
		   }else{
			   sdktask = new TSdktask();
			   sdktask.setTaskId(KeyHelper.createKey());
		   }
		   sdktask.setTaskName(taskName);
		   sdktask.setTaskDesc(taskDesc);
		   sdktask.setTaskStatus(taskStatus);
		   sdktask.setTaskVersion(taskVersion);
		   sdktask.setTaskPlugin(taskPlugin);
		   sdkTaskService.updateSdkTask(sdktask, bContent);
			return "redirect:/sdk/toTaskList.do";
		    }catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	}
	
	@RequestMapping(value = "/doDeleteSdkTask.do")
	public String doDeleteSdkTask(HttpServletRequest request) throws Exception{
		String taskId = stringOf(request.getParameter("taskId"));
		sdkTaskService.deleteSdkTask(taskId);
		return "redirect:/sdk/toTaskList.do";
	}
	
	
	@RequestMapping(value = "/toTaskCrossList.do")
	public String toTaskCrossList(HttpServletRequest request) throws Exception{
		String pageNumber = request.getParameter("pageNumber")==null?"1":request.getParameter("pageNumber");
		String taskId = stringOf(request.getParameter("taskId"));
		String appId = stringOf(request.getParameter("appId"));
		String channelId = stringOf(request.getParameter("channelId"));
		String provinceId = stringOf(request.getParameter("provinceId"));
		String taskStep = stringOf(request.getParameter("taskStep"));
		TSdktaskCross sdktaskCross = new TSdktaskCross();
		sdktaskCross.setTaskId(taskId);
		sdktaskCross.setAppId(appId);
		sdktaskCross.setChannelId(channelId);
		sdktaskCross.setProvinceId(provinceId);
		sdktaskCross.setTaskStep(taskStep);
		int items = sdkTaskService.getSdkTaskCrossItems(sdktaskCross);
		Page page = new Page();
		page.setPageAllCount(items);
		page.setPageNumber(Integer.parseInt(pageNumber));
		List<TSdktaskCross> sdktcs = sdkTaskService.getSdkTaskCrossList(sdktaskCross, page);
		TSdktask sdktask = sdkTaskService.getSdkTask(taskId);
		List<TSdkApp> apps = appService.getAllApps();
		List<TChannel> channels = channelService.getAllChannels();
		List<TProvince> provinces = dicService.getAllProvinces();
		List<TDic> taskSteps = dicService.getDicsForDTypeId(Global.DicID.SDK_TASK_STEP);
		request.setAttribute("taskId", taskId);
		request.setAttribute("page", page);
		request.setAttribute("sdktaskCross", sdktaskCross);
		request.setAttribute("sdktask", sdktask);
		request.setAttribute("sdktcs", sdktcs);
		request.setAttribute("apps", apps);
		request.setAttribute("channels", channels);
		request.setAttribute("provinces", provinces);
		request.setAttribute("taskSteps", taskSteps);
		
		return "/sdkTask/listSdkTaskCross";
	}
	
	@RequestMapping(value = "/toUpdateSdkTaskCross.do")
	public String toUpdateSdkTaskCross(HttpServletRequest request)  throws Exception{
		String taskId = stringOf(request.getParameter("taskId"));
		String appId = stringOf(request.getParameter("appId"));
		String channelId = stringOf(request.getParameter("channelId"));
		String provinceId = stringOf(request.getParameter("provinceId"));
		String taskStep = stringOf(request.getParameter("taskStep"));
		TSdktaskCrossKey key = new TSdktaskCrossKey();
		key.setTaskId(taskId);
		key.setAppId(appId);
		key.setChannelId(channelId);
		key.setProvinceId(provinceId);
		key.setTaskStep(taskStep);
		TSdktaskCross stc = sdkTaskService.selectByPrimaryKey(key);
		List<TSdkApp> apps = appService.getAllApps();
		List<TChannel> channels = channelService.getAllChannels();
		List<TProvince> provinces = dicService.getAllProvinces();
		List<TDic> taskSteps = dicService.getDicsForDTypeId(Global.DicID.SDK_TASK_STEP);
		request.setAttribute("taskId", taskId);
		request.setAttribute("stc", stc);
		request.setAttribute("apps", apps);
		request.setAttribute("channels", channels);
		request.setAttribute("provinces", provinces);
		request.setAttribute("taskSteps", taskSteps);
		return "/sdkTask/updateSdkTaskCross";
	}
	
	@RequestMapping(value = "/doUpdateSdkTaskCross.do")
	public void doUpdateSdkTaskCross(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			String taskId = stringOf(request.getParameter("taskId"));
			String appId = stringOf(request.getParameter("appId"));
			String channelId = stringOf(request.getParameter("channelId"));
			String provinceId = stringOf(request.getParameter("provinceId"));
			String taskStep = stringOf(request.getParameter("taskStep"));
			Integer taskExecute = isEmpty(request.getParameter("taskExecute"))?null:Integer.valueOf(request.getParameter("taskExecute"));
			TSdktaskCross sdktaskCross = new TSdktaskCross();
			sdktaskCross.setTaskId(taskId);
			sdktaskCross.setAppId(appId);
			sdktaskCross.setChannelId(channelId);
			sdktaskCross.setProvinceId(provinceId);
			sdktaskCross.setTaskStep(taskStep);
			sdktaskCross.setTaskExecute(taskExecute);
			String returnMsg = sdkTaskService.updateSdkTaskCross(sdktaskCross);
			response.getWriter().write(returnMsg);
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("FAIL");
		}
	}
	
	@RequestMapping(value = "/doDeleteSdkTaskCross.do")
	public String doDeleteSdkTaskCross(HttpServletRequest request) throws Exception{
		String taskId = stringOf(request.getParameter("taskId"));
		String appId = stringOf(request.getParameter("appId"));
		String channelId = stringOf(request.getParameter("channelId"));
		String provinceId = stringOf(request.getParameter("provinceId"));
		String taskStep = stringOf(request.getParameter("taskStep"));
		TSdktaskCrossKey key = new TSdktaskCrossKey();
		key.setTaskId(taskId);
		key.setAppId(appId);
		key.setChannelId(channelId);
		key.setProvinceId(provinceId);
		key.setTaskStep(taskStep);
		sdkTaskService.deleteSdkTaskCross(key);
		return "redirect:/sdk/toTaskCrossList.do?taskId="+taskId+"&appId="+appId+"&channelId="+channelId+"&provinceId="+provinceId+"&taskStep="+taskStep;
	}
	
	@RequestMapping(value = "/toTaskGlobalList.do")
	public String toTaskGlobalList(HttpServletRequest request) throws Exception{
		String pageNumber = request.getParameter("pageNumber")==null?"1":request.getParameter("pageNumber");
		String taskId = stringOf(request.getParameter("taskId"));
		String taskStep = stringOf(request.getParameter("taskStep"));
		TSdktaskGlobal sdktaskGlobal = new TSdktaskGlobal();
		sdktaskGlobal.setTaskId(taskId);
		sdktaskGlobal.setTaskStep(taskStep);
		int items = sdkTaskService.getSdkTaskGlobalItems(sdktaskGlobal);
		Page page = new Page();
		page.setPageAllCount(items);
		page.setPageNumber(Integer.parseInt(pageNumber));
		List<TSdktaskGlobal> sdktgs = sdkTaskService.getSdkTaskGlobalList(sdktaskGlobal, page);
		TSdktask sdktask = sdkTaskService.getSdkTask(taskId);
		List<TDic> taskSteps = dicService.getDicsForDTypeId(Global.DicID.SDK_TASK_STEP);
		request.setAttribute("taskId", taskId);
		request.setAttribute("page", page);
		request.setAttribute("sdktask", sdktask);
		request.setAttribute("sdktgs", sdktgs);
		request.setAttribute("taskSteps", taskSteps);
		
		return "/sdkTask/listSdkTaskGlobal";
	}
	
	@RequestMapping(value = "/toUpdateSdkTaskGlobal.do")
	public String toUpdateSdkTaskGlobal(HttpServletRequest request)  throws Exception{
		String taskId = stringOf(request.getParameter("taskId"));
		TSdktaskGlobal stg = sdkTaskService.getSdktaskGlobal(taskId);
		List<TDic> taskSteps = dicService.getDicsForDTypeId(Global.DicID.SDK_TASK_STEP);
		request.setAttribute("taskId", taskId);
		request.setAttribute("stg", stg);
		request.setAttribute("taskSteps", taskSteps);
		return "/sdkTask/updateSdkTaskGlobal";
	}
	
	@RequestMapping(value = "/doUpdateSdkTaskGlobal.do")
	public void doUpdateSdkTaskGlobal(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			String taskId = stringOf(request.getParameter("taskId"));
			String taskStep = stringOf(request.getParameter("taskStep"));
			Integer taskExecute = isEmpty(request.getParameter("taskExecute"))?null:Integer.valueOf(request.getParameter("taskExecute"));
			TSdktaskGlobal sdktaskGlobal = new TSdktaskGlobal();
			sdktaskGlobal.setTaskId(taskId);
			sdktaskGlobal.setTaskStep(taskStep);
			sdktaskGlobal.setTaskExecute(taskExecute);
			sdkTaskService.updateSdkTaskGlobal(sdktaskGlobal);
			response.getWriter().write("OK");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("FAIL");
		}
	}
	
	@RequestMapping(value = "/doDeleteSdkTaskGlobal.do")
	public void doDeleteSdkTaskGlobal(HttpServletRequest request) throws Exception{
		String taskId = stringOf(request.getParameter("taskId"));
		String taskStep = stringOf(request.getParameter("taskStep"));
		TSdktaskGlobalKey key = new TSdktaskGlobalKey();
		key.setTaskId(taskId);
		key.setTaskStep(taskStep);
		sdkTaskService.deleteSdkTaskGlobal(key);
	}
	
	@RequestMapping(value = "/toUpdateSdkTaskPhone.do")
	public String toUpdateSdkTaskPhone(HttpServletRequest request)  throws Exception{
		String taskId = stringOf(request.getParameter("taskId"));
		TSdktask sdktask = sdkTaskService.getSdkTask(taskId);
		List<TDic> taskSteps = dicService.getDicsForDTypeId(Global.DicID.SDK_TASK_STEP);
		request.setAttribute("taskId", taskId);
		request.setAttribute("sdktask", sdktask);
		request.setAttribute("taskSteps", taskSteps);
		return "/sdkTask/updateSdkTaskPhone";
	}
	
	@RequestMapping(value = "/doUpdateSdkTaskPhone.do")
	public void doUpdateSdkTaskPhone(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			String taskId = stringOf(request.getParameter("taskId"));
			String  imeis = stringOf(request.getParameter("imeis"));
			String  taskStep = stringOf(request.getParameter("taskStep"));
			Integer taskExecute = isEmpty(request.getParameter("taskExecute"))?null:Integer.valueOf(request.getParameter("taskExecute"));
			String[] imeix = imeis.split(",");
			List<String> imeiList = Arrays.asList(imeix);
			sdkTaskService.updateSdkTaskPhones(imeiList, taskId, taskStep, taskExecute);
			response.getWriter().write("OK");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/doDelSdkTaskPhone.do")
	public void doDelSdkTaskPhone(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			String taskId = stringOf(request.getParameter("taskId"));
			String  imeis = stringOf(request.getParameter("imeis"));
			String[] imeix = imeis.split(",");
			List<String> imeiList = Arrays.asList(imeix);
			sdkTaskService.delSdkTaskPhones(imeiList, taskId);
			response.getWriter().write("OK");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write(e.getMessage());
		}
	}
	
}
