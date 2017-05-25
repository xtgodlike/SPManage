package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TMobileBlacklist;

@Component @MyBatisRepository
public interface MobileBlacklistDao {
    int deleteByPrimaryKey(String mobile);

    int insert(TMobileBlacklist record);

    int insertSelective(TMobileBlacklist record);

    TMobileBlacklist selectByPrimaryKey(String mobile);

    int updateByPrimaryKeySelective(TMobileBlacklist record);

    int updateByPrimaryKey(TMobileBlacklist record);
    
    int deleteBatch(@Param("mobiles") List<String> mobiles);
    
    int insertBatch(List<TMobileBlacklist> mobileBlacklists);
}