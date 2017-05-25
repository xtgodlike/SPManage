package com.qy.sp.manage.controller.base;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qy.sp.manage.common.utils.CaptchaServiceSingleton;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qy.sp.manage.common.utils.DigestUtils;
import com.qy.sp.manage.common.utils.Global;
import com.qy.sp.manage.common.utils.KeyHelper;
import com.qy.sp.manage.dto.LoginResponse;
import com.qy.sp.manage.dto.MainMenu;
import com.qy.sp.manage.dto.Page;
import com.qy.sp.manage.dto.TBlobContent;
import com.qy.sp.manage.dto.TChannel;
import com.qy.sp.manage.dto.TCp;
import com.qy.sp.manage.dto.TPiple;
import com.qy.sp.manage.dto.TRole;
import com.qy.sp.manage.dto.TUser;
import com.qy.sp.manage.dto.TUserCPKey;
import com.qy.sp.manage.dto.TUserChannelKey;
import com.qy.sp.manage.dto.TUserPipleKey;
import com.qy.sp.manage.dto.TUserRole;
import com.qy.sp.manage.dto.UserSession;
import com.qy.sp.manage.service.BlobContentService;
import com.qy.sp.manage.service.ChannelService;
import com.qy.sp.manage.service.CpService;
import com.qy.sp.manage.service.PipleService;
import com.qy.sp.manage.service.RoleService;
import com.qy.sp.manage.service.UserService;


@Controller
@RequestMapping(value = "/basics")
public class UserController extends BaseController {
	
	private static final long serialVersionUID = 1L;
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	@Resource
	private BlobContentService blobContentService;
	@Resource
	private PipleService pipleService;
	@Resource
	private ChannelService channelService;
	@Resource
	private CpService cpService;

	/**
	 * 跳转到用户登录页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toLogin.do")
	public String toLogin() throws Exception {
		
		return "/basics/Login";
	}

	/**
	 * 用户登录
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doLogin.do")
	public String doLogin(HttpServletRequest request) throws Exception {
		String uid = request.getParameter("userName");
		String password = request.getParameter("password");
		
		try {
			Boolean isResponseCorrect = Boolean.FALSE;
			String captchaId = request.getSession().getId();
			String responsestr = request.getParameter("iCode");
			isResponseCorrect = CaptchaServiceSingleton.getInstance().validateResponseForID(captchaId, responsestr);
			if(!isResponseCorrect){//验证码输入错误！
				request.setAttribute("eMessage", "验证码错误！");
				return "/basics/Login";
			}
		} catch (Exception e) {
			return "/basics/Login";
		}
		
		TUser user = new TUser();
		user.setUserName(uid);
		user.setPassword(password);
		request.setAttribute("user",user);

		LoginResponse resultRes = userService.login(user);
		if ("OK".equals(resultRes.getResultMessage())) {
			UserSession uSession = new UserSession();
			uSession.init(resultRes.getUser());
			request.getSession().setAttribute(Global.U_SESSION, uSession);
			// 获取左侧菜单
			//PurviewSessionObject purview = userServer.getMenus(uSession.getUserId(),user.getUserType());
			
			MainMenu mainMenu = userService.getMenu(uSession.getUserId());
			request.getSession().setAttribute(Global.MENUES, mainMenu);
			//request.getSession().setAttribute(Global.MENUES, purview);
//			return "redirect:/pages/basics/mainhome.jsp";
			return "redirect:/pages/basics/mainhome.jsp";
		} else {
			request.setAttribute("eMessage", resultRes.getResultMessage());
			return "/basics/Login";
		}
	}

	/**
	 * 退出登录
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/logOut.do")
	public String logOut(HttpServletRequest request) throws Exception {
		request.getSession().setAttribute(Global.U_SESSION, null);
		request.getSession().setAttribute(Global.MENUES, null);
		request.getSession().invalidate();
		return "redirect:/basics/toLogin.do";
	}

	/**
	 * 跳转到个人中心
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/userCenter.do")
	public String userCenter(HttpServletRequest request) throws Exception {
		UserSession uSession = (UserSession) request.getSession().getAttribute(Global.U_SESSION);
		int userType = uSession.getUserType();

		if(userType==Global.User_Type.NORMAL){ 
			TUser user = userService.getUserByUserId(uSession.getUserId());
			request.setAttribute("user", user);
			return "/basics/narmal_userHome";
		}else{
			//throw new Exception("用户类型错误！");
			return "/basics/error";
		}
		
//		String fileRoot = ClientProperty.getProperty("config", "DOC_SERVER_ROOT");
//		String channelPFName = "billing_platform_use_channel_V2.0.doc"; // 固定值
//		String cpPFName = "billing_platform_use_cp_V2.0.doc"; // 固定值
//		String channelPFPath = fileRoot+channelPFName;
//		String cpPFPath = fileRoot+cpPFName;
//		request.setAttribute("channelPFPath", channelPFPath);
//		request.setAttribute("cpPFPath", cpPFPath);
		
		//账号信息，需要根据不同的用户类型跳转到不同的页面
//		if(userType==Global.User_Type.CHANNEL_ADMIN){ //渠道管理员，只能修改回调接口地址
//			String channelId = uSession.getChannelID();
//			Channel channel = channelService.getChannelInfo(channelId); 
//			List<Host> hosts = hostService.getAllHost();
//			for (int i = 0; i < hosts.size(); i++) {
//				Host host = hosts.get(i);
//				List<HostChargeFee> channelFees = channelService.getChannelChargeFeeList(channel.getChannelID(),String.valueOf(host.getHostID()));
//				host.setHostChargeFees(channelFees);
//			}
//			request.setAttribute("channel", channel);
//			request.setAttribute("hosts", hosts);
//			return "channel";
//		}else if(userType==Global.User_Type.CP_ADMIN || userType==Global.User_Type.CP_ROUTINE){ //CP管理员 
//			List<Host> hosts = hostService.getAllHost();
//			CPInfo cpInfo = cpService.getCpInfoByUserId(uSession.getUserId());
//			for (int i = 0; i < hosts.size(); i++) {
//				Host host = hosts.get(i);
//				List<HostChargeFee> cpFees = cpService.getCPChargeFeeList(cpInfo.getCPID(),String.valueOf(host.getHostID())); 
//				host.setHostChargeFees(cpFees);
//			}
//			request.setAttribute("cpInfo", cpInfo);
//			request.setAttribute("hosts", hosts);
//			return "cpAdmin";
//		}else if(userType==Global.User_Type.NORMAL){ 
//			User user = userServer.getUserByUserId(uSession.getUserId());
//			request.setAttribute("user", user);
//			return "normal";
//		}else{
//			//throw new Exception("用户类型错误！");
//			return ERROR;
//		}
		
	}

	/**
	 * 跳转到用户信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/listUser.do")
	public String listUser(HttpServletRequest request) throws Exception {
		int pageNo = intOf(request.getParameter("pageNumber"), 1);
		String userAccount = stringOf(request.getParameter("userAccount"));
		String Name = stringOf(request.getParameter("Name"));
		int status = intOf(request.getParameter("status"), 0);
		String mobile = stringOf(request.getParameter("mobile"));

		TUser user = new TUser();
		user.setUserName(userAccount);
		user.setChnName(Name);
		user.setUserStatus(status);
		user.setTelephone(mobile);

		int allItems = userService.getUserItems(user);
		Page page = new Page();
		page.setPageNumber(pageNo);
		page.setPageAllCount(allItems);

		List<TUser> users = userService.getUserList(user, page);
		request.setAttribute("users", users);
		request.setAttribute("page", page);
		request.setAttribute("status", status);
		request.setAttribute("user", user);
		return "/basics/listUser";
	}

	/**
	 * 跳转到添加用户信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toAddUser.do")
	public String toAddUser(HttpServletRequest request) throws Exception {
//		List<TRole> roles = roleService.loadRolesByType(Global.User_Type.NORMAL);
		List<TRole> roles = roleService.loadAll();
		List<TUser> users = userService.loadLeaders();
		//List<Dept> depts = userServer.loadAllDept();
		List<TPiple> piples = pipleService.getAllPiples();
		List<TChannel> channels = channelService.getAllChannels();
		List<TCp> cps =cpService.getAllCps();
		//request.setAttribute("depts", depts);
		request.setAttribute("users", users);
		request.setAttribute("roles", roles);
		request.setAttribute("piples", piples);
		request.setAttribute("channels", channels);
		request.setAttribute("cps", cps);
		return "/basics/addUser";
	}

	/**
	 * 添加用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doAddUser.do")
	public String doAddUser(HttpServletRequest request) throws Exception {
		//String userAccount = request.getParameter("userAccount");
		String userName = stringOf(request.getParameter("userAccount"));
		String chnName = stringOf(request.getParameter("Name"));
		String telephone = stringOf(request.getParameter("mobile"));
		String cerNo = stringOf(request.getParameter("idNo"));
		Timestamp birthday = time2Of(request.getParameter("birthday"));
		//String passwd = stringOf(request.getParameter("passwd"));
		int status = intOf(request.getParameter("status"));
		int level = intOf(request.getParameter("level"));
		//String city = stringOf(request.getParameter("city"));
		String[] arrRole = request.getParameterValues("role");
		//String remark = stringOf(request.getParameter("remark"));
		String email = stringOf(request.getParameter("email"));
		//String[] depts = request.getParameterValues("dept");
		String[] arrPiple = request.getParameterValues("piple");
		String[] arrChannel = request.getParameterValues("channel");
		String[] arrCps = request.getParameterValues("cp");
		TUser user = new TUser();
		user.setUserId(KeyHelper.createKey());
		user.setUserName(userName);
		user.setChnName(chnName);
		user.setTelephone(telephone);
		user.setCerNo(cerNo);
		user.setBirthday(birthday);
		user.setPassword(userName);
		user.setNewpassword(DigestUtils.encrypt(DigestUtils.SHA_1, user.getPassword()));
		user.setUserStatus(status);
		user.setLevel(level);
		user.setEmail(email);
		user.setUserType(Global.User_Type.NORMAL);
		user.setBaseId(0);
		//用户角色
		List<TUserRole> rolels = new ArrayList<TUserRole>();
		for (String string : arrRole) {
			TUserRole role = new TUserRole();
			role.setRoleId(string);
			role.setUserId(user.getUserId());
			rolels.add(role);
		}
		//用户通道
		List<TUserPipleKey> uPiples = new ArrayList<TUserPipleKey>();
		if(null != arrPiple){
    		for (String string : arrPiple) {
    			TUserPipleKey up = new TUserPipleKey();
    			up.setPipleId(string);
    			up.setUserId(user.getUserId());
    			uPiples.add(up);
    		}
		}
		//用户渠道
		List<TUserChannelKey> uChannels = new ArrayList<TUserChannelKey>();
		if(null != arrChannel){
    		for (String string : arrChannel) {
    			TUserChannelKey uc = new TUserChannelKey();
    			uc.setChannelId(string);
    			uc.setUserId(user.getUserId());
    			uChannels.add(uc);
    		}
		}
		
		List<TUserCPKey> ucps = new ArrayList<TUserCPKey>();
		if(null != arrCps){
			for (String string : arrCps) {
				TUserCPKey ucp = new TUserCPKey();
				ucp.setCpId(string);
				ucp.setUserId(user.getUserId());
				ucps.add(ucp);
			}
		}
		
		/*用户部门配置*/
		/*List<UserDept> udls = new ArrayList<UserDept>();
		if(!isEmpty(depts)){
			for (String d : depts) {
				UserDept ud = new UserDept();
				ud.setUserId(user.getUserId());
				ud.setDeptId(d);
				udls.add(ud);
			}
		}*/
		
		int itmes = userService.loadItemsByUserAccount(userName);
		if (itmes > 0) {
			List<TRole> roles = roleService.loadAll();
			request.setAttribute("roles", roles);
			request.setAttribute("eMessage", "该账号已被注册");
			request.setAttribute("user", user);
			return "/basics/addUser";
		} else {
			userService.addUser(user, rolels,uPiples,uChannels,ucps);
			return "redirect:/basics/listUser.do";
		}
	}
	
	/**
	 * 验证用户名是否可用
	 * @throws Exception
	 */
	@RequestMapping(value="/verifyUserAccount.do")
	public void verifyUserAccount(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/plain;charset=UTF-8");
		String userAccount = request.getParameter("userAccount");
		
		int itmes = userService.loadItemsByUserAccount(userAccount);
		if (itmes > 0) {
			response.getWriter().write("FAIL");
		}else{
			response.getWriter().write("OK");
		}
	}
	

	/**
	 * 跳转到修改用户信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toUpdateUser.do")
	public String toUpdateUser(HttpServletRequest request) throws Exception {
		String userId = request.getParameter("userId");
		TUser user = userService.getUserByUserId(userId);

//		List<TRole> roles = roleService.loadRolesByType(Global.User_Type.NORMAL);
		List<TRole> roles = roleService.loadAll();
		List<TUser> users = userService.loadLeaders();
		List<TUserRole> uRoles = roleService.loadRoleByUserId(userId);
		//List<Dept> depts = userServer.loadAllDept();
		//List<UserDept> uds = userServer.getUserDept(userId);
		List<TPiple> piples = pipleService.getAllPiples();
		List<TUserPipleKey> uPiples = userService.getUserPiples(userId);
		List<TChannel> channels = channelService.getAllChannels();
		List<TUserChannelKey> uChannels = userService.getUserChannels(userId);
		
		List<TCp> cps = cpService.getAllCps();
		List<TUserCPKey> uCPs = cpService.getUserCPs(userId);
		
		//request.setAttribute("uds", uds);
		//request.setAttribute("depts", depts);
		request.setAttribute("uRoles", uRoles);
		request.setAttribute("users", users);
		request.setAttribute("roles", roles);
		request.setAttribute("user", user);
		request.setAttribute("piples", piples);
		request.setAttribute("uPiples", uPiples);
		request.setAttribute("channels", channels);
		request.setAttribute("uChannels", uChannels);
		request.setAttribute("cps", cps);
		request.setAttribute("uCPs", uCPs);
		return "/basics/updateUser";
	}

	/**
	 * 修改用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doUpdateUser.do")
	public String doUpdateUser(HttpServletRequest request) throws Exception {
		String userId = request.getParameter("userId");
		String userName = stringOf(request.getParameter("userAccount"));
		String chnName = stringOf(request.getParameter("Name"));
		String telephone = stringOf(request.getParameter("mobile"));
		String cerNo = stringOf(request.getParameter("idNo"));
		Timestamp birthday = time2Of(request.getParameter("birthday"));
		String passwd = stringOf(request.getParameter("passwd"));
		int status = intOf(request.getParameter("status"));
		//String leader = stringOf(request.getParameter("leader"));
		int level = intOf(request.getParameter("level"));
		//String city = stringOf(request.getParameter("city"));
		String[] arrRole = request.getParameterValues("role");
		String[] arrPiple = request.getParameterValues("piple");
		String[] arrChannel = request.getParameterValues("channel");
		String[] arrCps = request.getParameterValues("cp");
		String email = stringOf(request.getParameter("email"));
		//String[] depts = request.getParameterValues("dept");
		
		TUser user = userService.loadUserByuserId(userId);
//		user.setUserName(userName);
		user.setChnName(chnName);
		user.setTelephone(telephone);
		user.setCerNo(cerNo);
		user.setBirthday(birthday);
//		user.setPassword(passwd);
		user.setUserStatus(status);
		user.setLevel(level);
		//user.setCity(city);
		user.setEmail(email);
		
		//用户角色
		List<TUserRole> rolels = new ArrayList<TUserRole>();
		if(!isEmpty(arrRole)){
			for (String string : arrRole) {
				TUserRole role = new TUserRole();
				role.setRoleId(string);
				role.setUserId(user.getUserId());
				rolels.add(role);
			}
		}
		//用户通道
		List<TUserPipleKey> uPiples = new ArrayList<TUserPipleKey>();
		if(!isEmpty(arrPiple)){
			for (String string : arrPiple) {
				TUserPipleKey up = new TUserPipleKey();
				up.setPipleId(string);
				up.setUserId(user.getUserId());
				uPiples.add(up);
			}
		}
		
		//用户渠道
		List<TUserChannelKey> uChannels = new ArrayList<TUserChannelKey>();
		if(!isEmpty(arrChannel)){
			for (String string : arrChannel) {
				TUserChannelKey uc = new TUserChannelKey();
				uc.setChannelId(string);
				uc.setUserId(user.getUserId());
				uChannels.add(uc);
			}
		}
		List<TUserCPKey> ucps = new ArrayList<TUserCPKey>();
		if(null != arrCps){
			for (String string : arrCps) {
				TUserCPKey ucp = new TUserCPKey();
				ucp.setCpId(string);
				ucp.setUserId(user.getUserId());
				ucps.add(ucp);
			}
		}
		/*用户部门配置*/
		/*List<UserDept> udls = new ArrayList<UserDept>();
		if(!isEmpty(depts)){
			for (String d : depts) {
				UserDept ud = new UserDept();
				ud.setUserId(user.getUserId());
				ud.setDeptId(d);
				
				udls.add(ud);
			}
		}*/
		userService.updateUser(user, rolels,uPiples,uChannels,ucps);
		
		return "redirect:/basics/listUser.do";
	}

	/**
	 * 删除用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doDeleteUser.do")
	public String doDeleteUser(HttpServletRequest request) throws Exception {
		String userId = request.getParameter("userId");
		userService.deleteUser(userId);
		return "redirect:/basics/listUser.do";
	}

	/**
	 * 重置密码
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value="/resetPassword.do")
	public void resetPassword(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/plain;charset=UTF-8");
		String userId = request.getParameter("userId");
		try {
			TUser user = userService.getUserByUserId(userId);
			user.setPassword(user.getUserName());
			user.setNewpassword(DigestUtils.encrypt(DigestUtils.SHA_1, user.getUserName()));
			userService.updateUser(user);
			response.getWriter().write("OK");
		} catch (Exception e) {
			response.getWriter().write("FAIL");
		}
	}

	/**
	 * 跳转到个人中心修改用户信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toUpdateUserInfo.do")
	public String toUpdateUserInfo(HttpServletRequest request) throws Exception {
		UserSession uSession = (UserSession) request.getSession().getAttribute(Global.U_SESSION);
		String userId = uSession.getUserId();

		TUser user = userService.getUser(userId);
		request.setAttribute("user", user);
		return "/basics/updateCurrUser";
	}

	/**
	 * 个人中心修改用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping(value="/doUpdateCurrUser")
//	public void doUpdateCurrUser() throws Exception {
//		try {
//			UserSession uSession = (UserSession) request.getSession().getAttribute(Global.U_SESSION);
//			String userId = uSession.getUserId();
//			
//			String linkMan = request.getParameter("linkMan");
//			String mobile = request.getParameter("mobile");
//			String email = request.getParameter("email");
//
//			TUser user = userService.getUserByUserId(userId);
//			user.setTelephone(mobile);
//			user.setEmail(email);
//
//			userService.updateUser(user);.updateUser(user,linkMan);
//			
//			response.getWriter().write("OK");
//		} catch (Exception e) {
//			response.getWriter().write("FAIL");
//		}
//		
//	}

	/**
	 * 跳转到修改密码页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toUpdatePassword.do")
	public String toUpdatePassword(HttpServletRequest request) throws Exception {
		UserSession uSession = (UserSession) request.getSession().getAttribute(Global.U_SESSION);
		String userId = uSession.getUserId();
		
		TUser user = userService.getUserByUserId(userId);
		request.setAttribute("user", user);
		return "/basics/updatePassword";
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doUpdatePassword.do")
	public void doUpdatePassword(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			UserSession uSession = (UserSession) request.getSession().getAttribute(Global.U_SESSION);
			String userId = uSession.getUserId();
			
			String oldPasswd = request.getParameter("passwd");
			String newPasswd = request.getParameter("newPasswd"); // 新密码

			TUser user = userService.getUserByUserId(userId);
			if (DigestUtils.encrypt(DigestUtils.SHA_1, oldPasswd).equals(user.getNewpassword())) {
				user.setPassword(newPasswd);
				user.setNewpassword(DigestUtils.encrypt(DigestUtils.SHA_1, newPasswd));
				userService.updateUser(user);
				response.getWriter().write("OK");
			} else { //原密码输入错误！
				response.getWriter().write("FAIL_0");
			}
		} catch (Exception e) {
			response.getWriter().write("FAIL_1");
		}
	}

	/**
	 * 404错误页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/pageNotFound404.do")
	public String pageNotFound404() throws Exception {
		
		return "/basics/404";
	}
	
	/**
	 * 系统错误页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/sysError.do")
	public String sysError(HttpServletRequest request) throws Exception {
		String errorMsg = request.getParameter("errorMsg")==null?"":request.getParameter("errorMsg").toString();
		new String(errorMsg.getBytes("ISO-8859-1"), "UTF-8"); // 中文参数强转UTF-8编码
		
		request.setAttribute("errorMsg", errorMsg);
		return "/basics/error";
	}
	
	
	/**
	 * 修改渠道账号基本信息
	 * @return
	 * @throws Exception
	 */
//	public void doUpdateCurrentAccount() throws Exception{
//		try {
//			UserSession uSession = (UserSession) request.getSession().getAttribute(Global.U_SESSION);
//			int userType = uSession.getUserType();
//			//账号信息，需要根据不同的用户类型跳转到不同的页面
//			if(userType==Global.User_Type.CHANNEL_ADMIN){ //渠道管理员，只能修改回调接口地址
//				String channelId = uSession.getChannelID();
//				String callBackUrl = stringOf(request.getParameter("callBackUrl"));
//				String channelPwd = stringOf(request.getParameter("apiPwd"));
//				
//				Channel c = new Channel();
//				c.setChannelID(channelId);
//				c.setCallBackUrl(callBackUrl.trim());
//				c.setChannelPwd(channelPwd);
//				channelService.updateCurrentChannel(c);
//			}else if(userType==Global.User_Type.CP_ADMIN){ //CP管理员
//				String callBackUrl = stringOf(request.getParameter("callBackUrl"));
//				CPInfo c = cpService.getCpInfo(uSession.getcPID());
//				c.setCallbackUrl(callBackUrl.trim());
//				cpService.updateCPInfo(c);
//			}else if(userType==Global.User_Type.NORMAL){
//				
//			}
//		} catch (Exception e) {
//			response.getWriter().write("FAIL");
//		}
//		response.getWriter().write("OK");
//	}
	
	@RequestMapping(value = "/downLoadFile.do")
	public void downLoadFile(HttpServletResponse response,String fileId) throws Exception{
		try {
			TBlobContent bContent = blobContentService.getBlobContentById(fileId);
			// 清空response
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename="+ java.net.URLEncoder.encode(bContent.getFilename(),"UTF-8"));
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(bContent.getFileContent());
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
