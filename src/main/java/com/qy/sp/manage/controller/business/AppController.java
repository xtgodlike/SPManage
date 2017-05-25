package com.qy.sp.manage.controller.business;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
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
import com.qy.sp.manage.common.utils.KeyHelper;
import com.qy.sp.manage.controller.base.BaseController;
import com.qy.sp.manage.dto.Page;
import com.qy.sp.manage.dto.TBlobContent;
import com.qy.sp.manage.dto.TCp;
import com.qy.sp.manage.dto.TSdkApp;
import com.qy.sp.manage.service.AppService;
import com.qy.sp.manage.service.CpService;
import com.qy.sp.manage.service.SdkConfigService;

@Controller
@RequestMapping(value = "/app")
public class AppController extends BaseController{
	
	private Logger log = Logger.getLogger(AppController.class);
	
	@Resource
	private AppService appService;
	@Resource
	private CpService cpService;
	@Resource
	private SdkConfigService sdkConfigService;

	@RequestMapping(value = "/toAppList.do")
	public String toAppList(HttpServletRequest request) throws Exception{
		String pageNumber = request.getParameter("pageNumber")==null?"1":request.getParameter("pageNumber");
		String appName = stringOf(request.getParameter("appName"));
		String cpId = stringOf(request.getParameter("cpId"));
		Integer appStatus = isEmpty(request.getParameter("appStatus"))?null:Integer.valueOf(request.getParameter("appStatus"));
		TSdkApp app = new TSdkApp();
		app.setAppName(appName);
		app.setCpId(cpId);
		app.setAppStatus(appStatus);
		
		int items = appService.getAppItems(app);
		Page page = new Page();
		page.setPageAllCount(items);
		page.setPageNumber(Integer.parseInt(pageNumber));
		List<TSdkApp> apps = appService.getAppList(app, page);
		List<TCp> cps = cpService.getAllCps();
		request.setAttribute("page", page);
		request.setAttribute("app", app);
		request.setAttribute("apps", apps);
		request.setAttribute("cps", cps);
		
		return "/app/listApp";
	}
	
	
	@RequestMapping(value = "/toUpdateApp.do")
	public String toUpdateApp(HttpServletRequest request)  throws Exception{
		String appId = stringOf(request.getParameter("appId"));
		TSdkApp app = appService.getApp(appId);
		List<TCp> cps = cpService.getAllCps();
		request.setAttribute("app", app);
		request.setAttribute("cps", cps);
		return "/app/updateApp";
	}
	
	@RequestMapping(value = "/verifyAppName.do")
	public void verifyAppName(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String appName = stringOf(new String(request.getParameter("appName").getBytes("iso-8859-1"),"utf-8"));
		String appId = stringOf(request.getParameter("appId"));
		if(!"".equals(appId) && appId!=null){
			TSdkApp app =  appService.getApp(appId);
			if(appName.equals(app.getAppName())){
				response.getWriter().write("OK");
				return;
			}
		}
		TSdkApp app = appService.selectByAppName(appName);
		if(app!=null){
			response.getWriter().write("FAIL");
		}else{
			response.getWriter().write("OK");
		}
	}
	
	@RequestMapping(value = "/doUpdateApp.do")
	public String doUpdateApp(HttpServletRequest request) throws Exception{
		    
		String appId = null;
		String cpId = null;
		String appName = null;
		String appPacketname = null;
		String appSigin = null;
		int appStatus = 0;
		String apkId = null;
		long appSize = 0;
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
				     if(fieldName.equals("appId")) appId = stringOf(fieldValue);
				     if(fieldName.equals("cpId")) cpId = stringOf(fieldValue);
				     if(fieldName.equals("appName")) appName = stringOf(fieldValue);
				     if(fieldName.equals("appPacketname")) appPacketname = stringOf(fieldValue);
				     if(fieldName.equals("appSigin")) appSigin = stringOf(fieldValue);
				     if(fieldName.equals("appStatus")) appStatus = intOf(fieldValue);
			    } else {
			    	  log.info("上传文件的大小:" + item.getSize());
			    	  log.info("上传文件的类型:" + item.getContentType());
			    	  log.info("上传文件的名称:" + item.getName());
				      String fileName = item.getName(); // 文件名
				      String fieldName = item.getFieldName(); // 参数名
				      appSize = item.getSize(); // 文件大小
				      InputStream fileStr = item.getInputStream();
				      byte[] fileByte = FileUtils.InputStreamToByte(fileStr);
				       if(fieldName.equals("apkId") && fileByte.length!=0){ // 任务插件
				    	bContent = new TBlobContent();
				    	apkId = KeyHelper.createKey();
				    	bContent.setFileId(apkId);
				    	bContent.setFilename(fileName);
				    	bContent.setFileContent(fileByte);
				      }
			    }
		    }
		   TSdkApp app = null;
		   if(appId!=null && !"".equals(appId)){
			   app = appService.getApp(appId);
			   if(app==null){
				   app = new TSdkApp();
				   app.setAppId(appId);
			   }
		   }else{
			   app = new TSdkApp();
			   app.setAppId(appService.getAppId());
		   }
		   app.setCpId(cpId);
		   app.setAppName(appName);
		   app.setAppPacketname(appPacketname);
		   app.setAppSigin(appSigin);
		   app.setAppSize(appSize);
		   app.setAppStatus(appStatus);
		   app.setApkId(apkId);
		   appService.updateApp(app, bContent);
			return "redirect:/app/toAppList.do";
		    }catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	}
}
