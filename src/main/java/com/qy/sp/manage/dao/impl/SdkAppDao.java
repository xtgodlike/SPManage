package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TSdkApp;

@Component @MyBatisRepository
public interface SdkAppDao {
    int deleteByPrimaryKey(String appId);

    int insert(TSdkApp record);

    int insertSelective(TSdkApp record);

    TSdkApp selectByPrimaryKey(String appId);

    int updateByPrimaryKeySelective(TSdkApp record);

    int updateByPrimaryKeyWithBLOBs(TSdkApp record);

    int updateByPrimaryKey(TSdkApp record);
    
    List<TSdkApp> getAll();
    
    List<TSdkApp> getAppList(@Param("app") TSdkApp app,@Param("start") int start,@Param("end") int end);
    
    int getAppItems(@Param("app") TSdkApp app);
    
    TSdkApp selectByAppName(String appName);
    public List<TSdkApp> getUserApps(String userId);
    
    List<TSdkApp> getCpAppList(@Param("app") TSdkApp app,@Param("start") int start,@Param("end") int end, @Param("userId") String userId);
    
    int getCpAppItems(@Param("app") TSdkApp app, @Param("userId") String userId);
    
    TSdkApp selectByPacketName(String packageName);
    
    int updateFileNameByPacketName(TSdkApp app);

   
}