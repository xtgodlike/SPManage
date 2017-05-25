package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TSdkconfigCross;
import com.qy.sp.manage.dto.TSdkconfigCrossKey;

@Component @MyBatisRepository
public interface SdkconfigCrossDao {
    int deleteByPrimaryKey(TSdkconfigCrossKey key);

    int insert(TSdkconfigCross record);

    int insertSelective(TSdkconfigCross record);

    TSdkconfigCross selectByPrimaryKey(TSdkconfigCrossKey key);
    List<TSdkconfigCross> selectSelective(TSdkconfigCrossKey key);

    int updateByPrimaryKeySelective(TSdkconfigCross record);

    int updateByPrimaryKey(TSdkconfigCross record);
    
    List<TSdkconfigCross> getSdkConfigCrossList(@Param("sdkconfigCross") TSdkconfigCross sdkconfigCross,@Param("start") int start,@Param("end") int end);
    
    int getSdkConfigCrossItems(TSdkconfigCross sdkconfigCross);
    
    int deleteByConfigId(String configId);
    
    TSdkconfigCross getByPrimaryKeyForCheck(TSdkconfigCrossKey key);
}