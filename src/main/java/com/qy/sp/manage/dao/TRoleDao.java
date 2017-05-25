package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.impl.RoleDao;
import com.qy.sp.manage.dto.TRole;

@Component
public class TRoleDao {
	@Resource
	private RoleDao roleDao;
	
   public int deleteByPrimaryKey(String roleId){
	   return roleDao.deleteByPrimaryKey(roleId);
   }

   public int insert(TRole record){
	   return roleDao.insert(record);
   }

   public int insertSelective(TRole record){
	   return roleDao.insertSelective(record);
   }

   public  TRole selectByPrimaryKey(String roleId){
	   return roleDao.selectByPrimaryKey(roleId);
   }

   public  int updateByPrimaryKeySelective(TRole record){
	   return roleDao.updateByPrimaryKeySelective(record);
   }

   public  int updateByPrimaryKey(TRole record){
	   return roleDao.updateByPrimaryKey(record);
   }
    
   public  int getRoleListSize(TRole role){
	   return roleDao.getRoleListSize(role);
   }
    
   public List<TRole> getRoleList(@Param("role") TRole role,@Param("start") int start,@Param("end") int end){
	   return roleDao.getRoleList(role, start, end);
   }
    
   public List<TRole> loadAll(){
	   return roleDao.loadAll();
   }
    
   public List<TRole> loadRolesByType(int roleType){
	   return roleDao.loadRolesByType(roleType);
   }
}