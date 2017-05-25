package com.qy.sp.manage.controller.base;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qy.sp.manage.common.utils.Global;
import com.qy.sp.manage.common.utils.KeyHelper;
import com.qy.sp.manage.common.utils.StringUtil;
import com.qy.sp.manage.dto.Page;
import com.qy.sp.manage.dto.RoleModuleObject;
import com.qy.sp.manage.dto.TRole;
import com.qy.sp.manage.dto.UserSession;
import com.qy.sp.manage.service.ModuleService;
import com.qy.sp.manage.service.RoleService;


@Controller
@RequestMapping(value = "/basics")
public class RoleController extends BaseController {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private RoleService roleService;
	@Resource
	private ModuleService moduleService;

	/**
	 * 跳转到用户角色列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/listRole.do")
	public String listRole(HttpServletRequest request) throws Exception {
		int pageNo = StringUtil.formatStringToInteger(request.getParameter("pageNumber"), 1);
		String roleName = request.getParameter("roleName") == null ? "" : request.getParameter("roleName");
		String roleStatus = request.getParameter("roleStatus") == null ? "0" : request.getParameter("roleStatus");
		if (roleStatus.equals("")) {
			roleStatus = "0";
		}
		TRole role = new TRole();
		role.setRoleName(roleName);
		role.setStatus(Integer.parseInt(roleStatus));

		int roleCount = roleService.getRoleListSize(role);

		Page page = new Page();
		page.setPageNumber(pageNo);
		page.setPageAllCount(roleCount);

		List<TRole> roles = roleService.getRoleList(page, role);

		//List<FlagDictionary> dicType = flagDictionaryServer.loadDictionaryByTypeId("3");

		request.setAttribute("rolex", role);
		//request.setAttribute("dicType", dicType);
		request.setAttribute("roles", roles);
		request.setAttribute("page", page);

		return "/basics/listRole";
	}

	/**
	 * 跳转到添加角色页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toAddRole.do")
	public String toAddRole() throws Exception {
		//List<FlagDictionary> dicType = flagDictionaryServer.loadDictionaryByTypeId("3");
		//request.setAttribute("dicType", dicType);
		return "/basics/addRole";
	}

	/**
	 * 添加用户角色
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doAddRole.do")
	public String doAddRole(HttpServletRequest request) throws Exception {
		String roleName = request.getParameter("roleName");
		String roleStatus = request.getParameter("roleStatus");
		String roleDesc = request.getParameter("roleDesc");
		
		UserSession uSession = (UserSession)request.getSession().getAttribute(Global.U_SESSION);
		String userId = uSession.getUserId();

		TRole role = new TRole();
		role.setRoleId(KeyHelper.createKey());
		role.setRoleDesc(roleDesc);
		role.setRoleName(roleName);
		role.setStatus(Integer.parseInt(roleStatus));
		role.setCreator(userId);
		role.setLastUpdateUser(userId);
		role.setRoleType(0);
		role.setWritable(Global.Role_Writable.WRITABLE);
		roleService.insertRole(role);

		return "redirect:/basics/listRole.do";
	}

	/**
	 * 跳转到修改角色页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toUpdateRole.do")
	public String toUpdateRole(HttpServletRequest request) throws Exception {
		String roleId = request.getParameter("roleId");
		//List<FlagDictionary> dicType = flagDictionaryServer.loadDictionaryByTypeId("3");
		TRole role = roleService.getRoleById(roleId);

		request.setAttribute("role", role);
		//request.setAttribute("dicType", dicType);
		return "/basics/updateRole";
	}

	/**
	 * 修改角色信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doUpdateRole.do")
	public String doUpdateRole(HttpServletRequest request) throws Exception {
		String roleId = request.getParameter("roleId");
		String roleName = request.getParameter("roleName");
		String roleStatus = request.getParameter("roleStatus");
		String roleDesc = request.getParameter("roleDesc");
		
		UserSession uSession = (UserSession)request.getSession().getAttribute(Global.U_SESSION);
		String userId = uSession.getUserId();

		TRole role = roleService.getRoleById(roleId);
		role.setRoleId(roleId);
		role.setRoleName(roleName);
		role.setStatus(Integer.parseInt(roleStatus));
		role.setRoleDesc(roleDesc);
		role.setLastUpdateUser(userId);

		roleService.updateRole(role);
		return "redirect:/basics/listRole.do";
	}

	/**
	 * 删除用户角色
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doDeleteRole.do")
	public String doDeleteRole(HttpServletRequest request) throws Exception {
		String roleId = request.getParameter("roleId");
		roleService.deleteRole(roleId);
		return "redirect:/basics/listRole.do";
	}

	/**
	 * 跳转至修改角色关联模块界面（分配模块）
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toModifyRoleModulePage.do")
	public String toModifyRoleModulePage(HttpServletRequest request) throws Exception {
		String roleId = request.getParameter("roleId");
		List<RoleModuleObject> rmos = moduleService.getRoleModuleObjectList(roleId);

		request.setAttribute("roleId", roleId);
		request.setAttribute("rmos", rmos);
		return "/basics/updateRoleModule";
	}

	/**
	 * 修模块角色信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doUpdateRoleModule.do")
	public String doUpdateRoleModule(HttpServletRequest request) throws Exception {
		String roleId = request.getParameter("roleId");
		String[] values = request.getParameterValues("modules");
		roleService.updateRoleModule(values, roleId);
		return "redirect:/basics/listRole.do";
	}

}
