package com.qy.sp.manage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qy.sp.manage.common.utils.DigestUtils;
import com.qy.sp.manage.common.utils.StringUtil;
import com.qy.sp.manage.dao.TModuleDao;
import com.qy.sp.manage.dao.TRoleDao;
import com.qy.sp.manage.dao.TUserCPDao;
import com.qy.sp.manage.dao.TUserChannelDao;
import com.qy.sp.manage.dao.TUserDao;
import com.qy.sp.manage.dao.TUserPipleDao;
import com.qy.sp.manage.dao.TUserRoleDao;
import com.qy.sp.manage.dto.LoginResponse;
import com.qy.sp.manage.dto.MainMenu;
import com.qy.sp.manage.dto.ModuleSessionObject;
import com.qy.sp.manage.dto.Page;
import com.qy.sp.manage.dto.PurviewSessionObject;
import com.qy.sp.manage.dto.TModule;
import com.qy.sp.manage.dto.TUser;
import com.qy.sp.manage.dto.TUserCPKey;
import com.qy.sp.manage.dto.TUserChannelKey;
import com.qy.sp.manage.dto.TUserPipleKey;
import com.qy.sp.manage.dto.TUserRole;

@Service
public class UserService{
	
	@Resource
	private TUserDao tUserDao;
	@Resource
	private TUserRoleDao tUserRoleDao;
	@Resource
	private TRoleDao tRoleDao;
	@Resource
	private TModuleDao tModuleDao;
	@Resource
	private TUserPipleDao tUserPipleDao;
	@Resource
	private TUserChannelDao tUserChannelDao;
	@Resource
	private TUserCPDao tUserCPDao;
//	private DeptDao deptDao;
//	private CPDao cPDao;
//	private ChannelDao channelDao;

	public void addUser(TUser user,List<TUserRole> uRole,List<TUserPipleKey> uPiples,List<TUserChannelKey> uChannels,List<TUserCPKey> ucps) throws Exception {
		//user.setPassword(DigestUtils.encrypt(DigestUtils.SHA_1, user.getPassword()));
		tUserDao.insert(user);
		
		//tUserRoleDao.delete(user.getUserId());
		for (TUserRole userRole : uRole) {
			tUserRoleDao.insert(userRole);
		}
		for (TUserPipleKey up : uPiples) {
			tUserPipleDao.insert(up);
		}
		for (TUserChannelKey uc : uChannels) {
			tUserChannelDao.insert(uc);
		}
		for (TUserCPKey ucp : ucps) {
			tUserCPDao.insert(ucp);
		}
		/*for (UserDept userDept : uds) {
			deptDao.saveUserDept(userDept);
		}*/
		
	}
	
	public List<TUser> getUserList(TUser user,Page page) throws Exception{
		return tUserDao.getUserList(user, page.getStartItems(), page.getPageSize());
	}
	
	public int getUserItems(TUser user) throws Exception{
		return tUserDao.getUserItems(user);
	}

	public TUser getUserByUserId(String userId) throws Exception {
		
		return tUserDao.selectByPrimaryKey(userId);
	}

	public void updateUser(TUser user,List<TUserRole> uRole,List<TUserPipleKey> uPiples,List<TUserChannelKey> uChannels,List<TUserCPKey> ucps) throws Exception {
		tUserDao.updateByPrimaryKeySelective(user);
		tUserRoleDao.deleteByUserId(user.getUserId());
		for (TUserRole userRole : uRole) {
			tUserRoleDao.insert(userRole);
		}
		tUserPipleDao.deleteByUserId(user.getUserId());
		for (TUserPipleKey up : uPiples) {
			tUserPipleDao.insert(up);
		}
		tUserChannelDao.deleteByUserId(user.getUserId());
		for (TUserChannelKey uc : uChannels) {
			tUserChannelDao.insert(uc);
		}
		tUserCPDao.deleteByUserId(user.getUserId());
		for (TUserCPKey ucp : ucps) {
			tUserCPDao.insert(ucp);
		}
		//添加用户部门
		/*deptDao.deleteUserDept(user.getUserId());
		for (UserDept userDept : uds) {
			deptDao.saveUserDept(userDept);
		}*/
		
	}

	public TUser loadUserByuserId(String userId) throws Exception {
		
		return tUserDao.selectByPrimaryKey(userId);
	}

	public void deleteUser(String userId) throws Exception {
		tUserDao.deleteByPrimaryKey(userId);
		//tRoleDaox.deleteUserDept(userId);
		tUserRoleDao.deleteByUserId(userId);
	}

	public LoginResponse login(TUser user) throws Exception {
		//user.setPassword(DigestUtils.encrypt(DigestUtils.SHA_1, user.getPassword()));
		TUser _user = tUserDao.loadUserByNameAndPwd(user.getUserName(), user.getPassword());
		boolean bOK = false;
		String result = null;
		if(null==_user){
			result = "用户名或者密码错误！";
		}else if(_user.getUserStatus()==0){ //未激活
			result = "用户未激活！";
		}else if(_user.getUserStatus()==1){//激活
			result = "OK";
			//判断用户加密密码字段是否有值，如果用户没有值，需要将明文密码加密后保存到用户表
			if(StringUtil.isBlank(_user.getNewpassword())){
				String newPassword = DigestUtils.encrypt(DigestUtils.SHA_1, user.getPassword());
				TUser newUser = new TUser();
				newUser.setUserId(_user.getUserId());
				newUser.setNewpassword(newPassword);
				tUserDao.updateByPrimaryKeySelective(newUser);
			}
			bOK = true;
		}else if(_user.getUserStatus()==2){//禁用
			result = "用户已被禁用！";
		}else{
			result = "登录异常！";
		}
		//如果用户是CP管理员、cp运营、渠道管理员
//		if(bOK){
//    		if((_user.getUserType() == Global.User_Type.CP_ADMIN)||(_user.getUserType() == Global.User_Type.CP_ROUTINE)||(_user.getUserType() == Global.User_Type.CHANNEL_ADMIN)){
//    			int cpStatus = cPDao.getCpStatus(_user.getCpId());
//    			if(cpStatus == Global.CP_OpStatus.UNAUDITED){
//    				result = "CP状态未审核";
//    			}
//    		}
//    		if((_user.getUserType() == Global.User_Type.CHANNEL_ADMIN)){
//    			int chStatus = cPDao.getChannelStatus(_user.getChannelId());
//    			if(chStatus ==  Global.OpStatus.OFF){
//    				result = "渠道状态关闭";
//    			}
//    		}
//		}
		LoginResponse rep = new LoginResponse();
		rep.setUser(_user);
		rep.setResultMessage(result);
		return rep;
	}
	
	
	public MainMenu getMenu(String userId) throws Exception{
		List<TModule> modules = null;
//		//蔡臻超：对于CP管理员，CP运营人员，渠道管理员，无视其角色关系，直接取固定
//		if(userType == Global.User_Type.CP_ADMIN){
//			modules = tModuleDao.getModuleByRoleId(Global.Roles_Fixed.CP_ADMIN);
//		}else if(userType == Global.User_Type.CP_ROUTINE){
//			modules = tModuleDao.getModuleByRoleId(Global.Roles_Fixed.CP_ROUTINE);
//		}else if(userType == Global.User_Type.CHANNEL_ADMIN){
//			modules = tModuleDao.getModuleByRoleId(Global.Roles_Fixed.CHANNEL_ADMIN);
//		}else{
//			modules = tModuleDao.getModuleByUserId(userId);
//		}
		modules = tModuleDao.getModuleByUserId(userId);
		MainMenu menu = new MainMenu(modules);
		return menu;
	}
	

	public PurviewSessionObject getMenus(String userId, int userType) throws Exception {
		
		List<TModule> modules = null;
		//蔡臻超：对于CP管理员，CP运营人员，渠道管理员，无视其角色关系，直接取固定
//		if(userType == Global.User_Type.CP_ADMIN){
//			modules = tModuleDao.getModuleByRoleId(Global.Roles_Fixed.CP_ADMIN);
//		}else if(userType == Global.User_Type.CP_ROUTINE){
//			modules = tModuleDao.getModuleByRoleId(Global.Roles_Fixed.CP_ROUTINE);
//		}else if(userType == Global.User_Type.CHANNEL_ADMIN){
//			modules = tModuleDao.getModuleByRoleId(Global.Roles_Fixed.CHANNEL_ADMIN);
//		}else{
//			modules = tModuleDao.getModuleByUserId(userId);
//		}
		
		//RootMenu menu = new RootMenu(modules);
		modules = tModuleDao.getModuleByUserId(userId);
		PurviewSessionObject purview = new PurviewSessionObject();
		
		List<ModuleSessionObject> showObj = new ArrayList<ModuleSessionObject>();
		//List<Module> buttons = new ArrayList<Module>(); //按钮
		List<TModule> limits = new ArrayList<TModule>(); //权限列表
		
		for (TModule module : modules) {
			if(module.getModuleLevel()==1){ //一级菜单需要显示
				if(module.getModuleType()==4){ //权限
					limits.add(module);
					continue;
				}
				
				ModuleSessionObject mso = new ModuleSessionObject();
				mso.setParentModule(module);
				
				List<TModule> childrenModules = new ArrayList<TModule>();
				for (TModule module2 : modules) {
					if(module.getModuleId().equals(module2.getParentMId())){//2级菜单
						
						if(module2.getModuleType()==2){
							childrenModules.add(module2);
						}else if(module2.getModuleType()==4){
							limits.add(module2);
						}
						
						//三级菜单
						for (TModule module3 : modules) {
							if(module2.getModuleId().equals(module3.getParentMId())){
								if(module3.getModuleType()==4){
									limits.add(module3);
								}
							}
						}
					}
					mso.setChildrenModules(childrenModules);
				}
				showObj.add(mso);
			}
		}
		purview.setMenu(showObj);
		//purview.setButtons(buttons);
		purview.setLimits(limits);
		return purview;
	}

	public int loadItemsByUserAccount(String userAccount) throws Exception {
		
		return tUserDao.loadItemsByUserAccount(userAccount);
	}

//	public void updatePassword(TUser user) throws Exception {
//		//user.setPassword(DigestUtils.encrypt(DigestUtils.SHA_1, user.getPassword()));
//		tUserDao.updatePassword(user);
//	}

	public TUser getUser(String userId) throws Exception {
		
		return tUserDao.selectByPrimaryKey(userId);
	}

	public void updateUser(TUser user) throws Exception {
//		int userType = user.getUserType();
//		
//		if(userType==Global.User_Type.CHANNEL_ADMIN){ //渠道管理员
//			//修改渠道联系人
//			Channel channel = new Channel();
//			channel.setChannelID(user.getChannelId());
//			channel.setLinkMan(linkMan);
//			channelDao.updateChannelLinkMan(channel);
//		}else if(userType==Global.User_Type.CP_ADMIN){
//			//修改CP联系人
//			CPInfo c = cPDao.getCpInfo(user.getCpId());
//			c.setLinkman(linkMan);
//			cPDao.updateCPInfo(c);
//		}
		tUserDao.updateByPrimaryKeySelective(user);
	}

	public List<TUser> loadAllUser() throws Exception {
		return tUserDao.loadAll();
	}

	public List<TUser> loadLeaders() throws Exception {
		return tUserDao.loadLeaders();
	}
	
	public List<TUserPipleKey> getUserPiples(String userId) throws Exception{
		return tUserPipleDao.getUserPiples(userId);
	}
	
	public List<TUserChannelKey> getUserChannels(String userId) throws Exception{
		return tUserChannelDao.getUserChannels(userId);
	}
}
