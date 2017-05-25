package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TSdktaskCross;
import com.qy.sp.manage.dto.TSdktaskCrossKey;

@Component @MyBatisRepository
public interface SdktaskCrossDao {
    int deleteByPrimaryKey(TSdktaskCrossKey key);

    int insert(TSdktaskCross record);

    int insertSelective(TSdktaskCross record);

    TSdktaskCross selectByPrimaryKey(TSdktaskCrossKey key);

    int updateByPrimaryKeySelective(TSdktaskCross record);

    int updateByPrimaryKey(TSdktaskCross record);
    
    List<TSdktaskCross> getSdkTaskCrossList(@Param("sdktaskCross") TSdktaskCross sdktaskCross,@Param("start") int start,@Param("end") int end);
    
    int getSdkTaskCrossItems(TSdktaskCross sdktaskCross);
    
    List<TSdktaskCross> getByPrimaryKeyForCheck(TSdktaskCrossKey key);
}