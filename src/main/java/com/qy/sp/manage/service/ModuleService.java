package com.qy.sp.manage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qy.sp.manage.common.utils.Global;
import com.qy.sp.manage.common.utils.KeyHelper;
import com.qy.sp.manage.common.utils.StringUtil;
import com.qy.sp.manage.dao.TModuleDao;
import com.qy.sp.manage.dao.TRoleModuleDao;
import com.qy.sp.manage.dto.Page;
import com.qy.sp.manage.dto.RoleModuleObject;
import com.qy.sp.manage.dto.TModule;
import com.qy.sp.manage.dto.TRoleModuleKey;

@Service
public class ModuleService{
	
	@Resource
	private TModuleDao tModuleDao;
	@Resource
	private TRoleModuleDao tRoleModuleDao;


	/*public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}*/

	public List<TModule> loadModuleLimit(TModule module, Page page) throws Exception {

		return tModuleDao.loadModuleLimit(module, page.getStartItems(), page.getPageSize());
	}

	public int loadItems(TModule module) throws Exception {

		return tModuleDao.loadItems(module);
	}

	public TModule getModuleById(String moduleId) throws Exception {

		return tModuleDao.selectByPrimaryKey(moduleId);
	}

	public void insertModule(TModule module) throws Exception {
		module.setModuleId(KeyHelper.creatKey(16));

		TModule parentModule = tModuleDao.selectByPrimaryKey(module.getParentMId());
		module.setModuleLevel(parentModule.getModuleLevel() + 1);

		tModuleDao.insert(module);
		//默认给系统管理员初始化该模块权限
		TRoleModuleKey roleModule = new TRoleModuleKey();
		roleModule.setModuleId(module.getModuleId());
		roleModule.setRoleId(Global.Roles_Fixed.ADMIN);
		tRoleModuleDao.insert(roleModule);
		
	}

	public void updateModule(TModule module) throws Exception {
		tModuleDao.updateByPrimaryKeySelective(module);
	}

	public void deleteModule(String moduleId) throws Exception {
		tModuleDao.deleteByPrimaryKey(moduleId);
		tModuleDao.deleteModuleByParentId(moduleId);
		tRoleModuleDao.deleteByModuleId(moduleId);
	}

	public List<RoleModuleObject> getRoleModuleObjectList(String roleId) throws Exception {

		List<RoleModuleObject> rmObjects = tRoleModuleDao.getRoleModuleObjectByRoleId(roleId);
		for (int i = 0; i < rmObjects.size(); i++) {
			RoleModuleObject rmo = rmObjects.get(i);
			if (!StringUtil.isNull(rmo.getRoleId())) {
				rmo.setIsChecked(1);
			}
		}
		return rmObjects;
		/*
		 * List<RoleModuleObject> newRoles = new ArrayList<RoleModuleObject>();
		 * //系统跟目录 for (RoleModuleObject roleModuleObject : roles) {
		 * if(roleModuleObject.getModuleLevel()==0){
		 * newRoles.add(roleModuleObject); break; } } for (RoleModuleObject
		 * roleModuleObject : roles) { int pLevel =
		 * roleModuleObject.getModuleLevel(); if(pLevel==1){ //一级菜单
		 * newRoles.add(roleModuleObject); for (RoleModuleObject
		 * roleModuleObject2 : roles) { //二级菜单
		 * if(roleModuleObject2.getParentMId(
		 * ).equals(roleModuleObject.getModuleId())){
		 * newRoles.add(roleModuleObject2);
		 * 
		 * for (RoleModuleObject roleModuleObject3 : roles) { //三级菜单
		 * if(roleModuleObject3
		 * .getParentMId().equals(roleModuleObject2.getModuleId())){
		 * newRoles.add(roleModuleObject3); } } } } } }
		 */
	}
}
