package com.qy.sp.manage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qy.sp.manage.dao.TRoleDao;
import com.qy.sp.manage.dao.TRoleModuleDao;
import com.qy.sp.manage.dao.TUserRoleDao;
import com.qy.sp.manage.dto.Page;
import com.qy.sp.manage.dto.TRole;
import com.qy.sp.manage.dto.TRoleModuleKey;
import com.qy.sp.manage.dto.TUserRole;

@Service
public class RoleService{
	
	@Resource
	private TRoleDao tRoleDao;
	@Resource
	private TRoleModuleDao tRoleModuleDao;
	@Resource
	private TUserRoleDao tUserRoleDao;
	
	public void insertRole(TRole role) throws Exception {
		tRoleDao.insert(role);
	}

	public int getRoleListSize(TRole role) throws Exception {
		
		return tRoleDao.getRoleListSize(role);
	}

	public List<TRole> getRoleList(Page page,TRole role) throws Exception {
		
		return tRoleDao.getRoleList(role,page.getStartItems(), page.getPageSize());
	}

	public TRole getRoleById(String roleId) throws Exception {
		
		return tRoleDao.selectByPrimaryKey(roleId);
	}

	public void updateRole(TRole role) throws Exception {
		tRoleDao.updateByPrimaryKeySelective(role);
	}

	public void deleteRole(String roleId) throws Exception {
		tRoleDao.deleteByPrimaryKey(roleId);
	}

	public void updateRoleModule(String[] value, String roleId)
			throws Exception {
		tRoleModuleDao.deleteByRoleId(roleId);
		
		if(null!=value && value.length>0){
			for (String val : value) {
				TRoleModuleKey roleModule = new TRoleModuleKey();
				roleModule.setModuleId(val);
				roleModule.setRoleId(roleId);
				tRoleModuleDao.insert(roleModule);
			}
		}
		
	}

	public List<TRole> loadAll() throws Exception {
		
		return tRoleDao.loadAll();
	}

	public List<TUserRole> loadRoleByUserId(String userId) throws Exception {
		return tUserRoleDao.loadRoleByUserId(userId);
	}

	public List<TRole> loadRolesByType(int roleType) throws Exception {
		return tRoleDao.loadRolesByType(roleType);
	}
}
