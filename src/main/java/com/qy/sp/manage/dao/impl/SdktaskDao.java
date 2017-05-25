package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TSdktask;

@Component @MyBatisRepository
public interface SdktaskDao {
    int deleteByPrimaryKey(String taskId);

    int insert(TSdktask record);

    int insertSelective(TSdktask record);

    TSdktask selectByPrimaryKey(String taskId);

    int updateByPrimaryKeySelective(TSdktask record);

    int updateByPrimaryKey(TSdktask record);
    
    List<TSdktask> getSdkTaskList(@Param("sdkTask") TSdktask sdkTask,@Param("start") int start,@Param("end") int end);
    
    int getSdkTaskItems(TSdktask sdktask);
    
    TSdktask selectByTaskName(String taskName);
}