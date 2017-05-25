package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.impl.UserRoleDao;
import com.qy.sp.manage.dto.TUserRole;
import com.qy.sp.manage.dto.TUserRoleKey;

@Component
public class TUserRoleDao {
	@Resource
	private UserRoleDao userRoleDao;
	
	public int deleteByPrimaryKey(TUserRoleKey key){
		return userRoleDao.deleteByPrimaryKey(key);
	}
    
	public int insert(TUserRole record){
		return userRoleDao.insert(record);
	}

	public int insertSelective(TUserRole record){
		return userRoleDao.insertSelective(record);
	}

	public TUserRole selectByPrimaryKey(TUserRoleKey key){
		return userRoleDao.selectByPrimaryKey(key);
	}

	public int updateByPrimaryKeySelective(TUserRole record){
		return userRoleDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TUserRole record){
		return userRoleDao.updateByPrimaryKey(record);
	}
    
	public List<TUserRole> loadRoleByUserId(String userId){
		return userRoleDao.loadRoleByUserId(userId);
	}
    
	public int deleteByRoleId(String roleId){
		return userRoleDao.deleteByRoleId(roleId);
	}
    
	public int deleteByUserId(String userId){
		return userRoleDao.deleteByUserId(userId);
	}
    
}