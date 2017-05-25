package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.RoleModuleObject;
import com.qy.sp.manage.dto.TRoleModuleKey;

@Component @MyBatisRepository
public interface RoleModuleDao {
    int deleteByPrimaryKey(TRoleModuleKey key);
    
    int deleteByModuleId(String moduleId);
    
    int deleteByRoleId(String roleId);

    int insert(TRoleModuleKey record);

    int insertSelective(TRoleModuleKey record);
    
    List<RoleModuleObject> getRoleModuleObjectByRoleId(String roleId);
}