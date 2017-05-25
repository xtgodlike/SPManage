package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.impl.RoleModuleDao;
import com.qy.sp.manage.dto.RoleModuleObject;
import com.qy.sp.manage.dto.TRoleModuleKey;

@Component
public class TRoleModuleDao{
	@Resource
	private RoleModuleDao roleModuleDao;
	
    public int deleteByPrimaryKey(TRoleModuleKey key){
    	return roleModuleDao.deleteByPrimaryKey(key);
    }
    
	public int deleteByModuleId(String moduleId){
		return roleModuleDao.deleteByModuleId(moduleId);
	}
    
	public int deleteByRoleId(String roleId){
		return roleModuleDao.deleteByRoleId(roleId);
	}

	public int insert(TRoleModuleKey record){
		return roleModuleDao.insert(record);
	}

	public int insertSelective(TRoleModuleKey record){
		return roleModuleDao.insertSelective(record);
	}
    
	public List<RoleModuleObject> getRoleModuleObjectByRoleId(String roleId){
		return roleModuleDao.getRoleModuleObjectByRoleId(roleId);
	}
}