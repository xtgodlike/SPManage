package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TRole;

@Component @MyBatisRepository
public interface RoleDao {
    int deleteByPrimaryKey(String roleId);

    int insert(TRole record);

    int insertSelective(TRole record);

    TRole selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(TRole record);

    int updateByPrimaryKey(TRole record);
    
    int getRoleListSize(TRole role);
    
    List<TRole> getRoleList(@Param("role") TRole role,@Param("start") int start,@Param("end") int end);
    
    List<TRole> loadAll();
    
    List<TRole> loadRolesByType(int roleType);
}