package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TUserRole;
import com.qy.sp.manage.dto.TUserRoleKey;

@Component @MyBatisRepository
public interface UserRoleDao {
    int deleteByPrimaryKey(TUserRoleKey key);
    
    int insert(TUserRole record);

    int insertSelective(TUserRole record);

    TUserRole selectByPrimaryKey(TUserRoleKey key);

    int updateByPrimaryKeySelective(TUserRole record);

    int updateByPrimaryKey(TUserRole record);
    
    List<TUserRole> loadRoleByUserId(String userId);
    
    int deleteByRoleId(String roleId);
    
    int deleteByUserId(String userId);
    
}