package com.qy.sp.manage.controller.base;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qy.sp.manage.dto.Page;
import com.qy.sp.manage.dto.TModule;
import com.qy.sp.manage.service.ModuleService;



@Controller
@RequestMapping(value = "/basics")
public class ModuleController extends BaseController{

	private Logger log = Logger.getLogger(ModuleController.class);
	
	@Resource
	private ModuleService moduleService;
	
	/**
	 * 跳转到用户信息列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/listModule.do")
	public String listModule(HttpServletRequest request) throws Exception{
		String pageNumber = request.getParameter("pageNumber")==null?"1":request.getParameter("pageNumber");
		String moduleName = request.getParameter("moduleName")==null?"":request.getParameter("moduleName");
		String moduleType = request.getParameter("moduleType")==null?"":request.getParameter("moduleType");
		if("".equals(moduleType)){
			moduleType = "0";
		}		
		TModule module = new TModule();
		module.setModuleName(moduleName);
		module.setModuleType(Integer.parseInt(moduleType));
		
		int items = moduleService.loadItems(module);
		Page page = new Page();
		page.setPageAllCount(items);
		page.setPageNumber(Integer.parseInt(pageNumber));
		List<TModule> modules = moduleService.loadModuleLimit(module, page);
		
		//List<FlagDictionary> dicTypes =  flagDictionaryServer.loadDictionaryByTypeId("2");
		
		request.setAttribute("module", module);
		request.setAttribute("modules", modules);
		request.setAttribute("page", page);
		//request.setAttribute("dics", dicTypes);
		
		return "/basics/listModule";
	}
	
	/**
	 * 跳转到添加子模块页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toAddModule.do")
	public String toAddModule(HttpServletRequest request) throws Exception{
		String parentModuleId = request.getParameter("parentModuleId");
		TModule module =  moduleService.getModuleById(parentModuleId);
		
		//List<FlagDictionary> dicStatus =  flagDictionaryServer.loadDictionaryByTypeId("1");
		//List<FlagDictionary> dicTypes =  flagDictionaryServer.loadDictionaryByTypeId("2");
		
		request.setAttribute("module", module);
		//request.setAttribute("dicTypes", dicTypes);
		//request.setAttribute("dicStatus", dicStatus);
		return "/basics/addModule";
	}
	
	/**
	 * 添加模块
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doAddModule.do")
	public String doAddModule(HttpServletRequest request) throws Exception{
		String parentModuleId = request.getParameter("parentModuleId");
		String moduleName = request.getParameter("moduleName");
		String moduleLink = request.getParameter("moduleLink");
		String showSeq = request.getParameter("showSeq");
		String status = request.getParameter("status");
		String moduleType = request.getParameter("moduleType");
		String moduleDesc = request.getParameter("moduleDesc");
		String parentModuleCode = request.getParameter("parentModuleCode");
		String moduleCode = request.getParameter("moduleCode").toUpperCase(); // 转大写
		Integer showType = isEmpty(request.getParameter("showType"))?null:Integer.valueOf(request.getParameter("showType"));
		
		TModule module = new TModule();
		module.setParentMId(parentModuleId);
		module.setModuleName(moduleName);
		module.setModuleLink(moduleLink);
		module.setShowSeq(Integer.parseInt(showSeq));
		module.setStatus(Integer.parseInt(status));
		module.setModuleType(Integer.parseInt(moduleType));
		module.setModuleDesc(moduleDesc);
		module.setModuleCode(parentModuleCode+"."+moduleCode); // 拼接模块代码以父模块代码开头
		module.setShowType(showType);
		
		moduleService.insertModule(module);
		
		return "redirect:/basics/listModule.do";
	}
	
	
	/**
	 * 跳转到修改模块页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toUpdateModule.do")
	public String toUpdateModule(HttpServletRequest request) throws Exception{
		String moduleId = request.getParameter("moduleId");
		String parentModuleId = request.getParameter("parentModuleId");
		
		TModule module = moduleService.getModuleById(moduleId);
		TModule pModule = moduleService.getModuleById(parentModuleId);
		
		//List<FlagDictionary> dicStatus =  flagDictionaryServer.loadDictionaryByTypeId("1");
		//List<FlagDictionary> dicTypes =  flagDictionaryServer.loadDictionaryByTypeId("2");
		
		//request.setAttribute("dicTypes", dicTypes);
		//request.setAttribute("dicStatus", dicStatus);
		request.setAttribute("module", module);
		request.setAttribute("pModuleName", pModule.getModuleName());
		return "/basics/updateModule";
	}
	
	/**
	 * 修改模块
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doUpdateModule.do")
	public String doUpdateModule(HttpServletRequest request) throws Exception{
		String moduleId = request.getParameter("moduleId");
		String moduleName = request.getParameter("moduleName");
		String moduleLink = request.getParameter("moduleLink");
		
		String status = request.getParameter("status");
		//String moduleType = request.getParameter("moduleType");
		String showSeq = request.getParameter("showSeq");
		String moduleDesc = request.getParameter("moduleDesc");
		Integer showType = isEmpty(request.getParameter("showType"))?null:Integer.valueOf(request.getParameter("showType"));
		
		TModule module = moduleService.getModuleById(moduleId);
		module.setModuleId(moduleId);
		module.setModuleName(moduleName);
		module.setModuleLink(moduleLink);
		module.setStatus(Integer.parseInt(status));
		//module.setModuleType(Integer.parseInt(moduleType));
		module.setShowSeq(Integer.parseInt(showSeq));
		module.setModuleDesc(moduleDesc);
		module.setShowType(showType);
		
		moduleService.updateModule(module);

		return "redirect:/basics/listModule.do";
	}
	
	/**
	 * 删除模块（删除父模块时，需要删除其下面的所有子模块）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doDeleteModule.do")
	public String doDeleteModule(HttpServletRequest request) throws Exception{
		String moduleId = request.getParameter("moduleId");
		moduleService.deleteModule(moduleId);
		return "redirect:/basics/listModule.do";
	}
}
