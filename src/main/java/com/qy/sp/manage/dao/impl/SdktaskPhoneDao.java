package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TSdktaskPhone;
import com.qy.sp.manage.dto.TSdktaskPhoneKey;

@Component @MyBatisRepository
public interface SdktaskPhoneDao {
    int deleteByPrimaryKey(TSdktaskPhoneKey key);

    int insert(TSdktaskPhone record);

    int insertSelective(TSdktaskPhone record);

    TSdktaskPhone selectByPrimaryKey(TSdktaskPhoneKey key);

    int updateByPrimaryKeySelective(TSdktaskPhone record);

    int updateByPrimaryKey(TSdktaskPhone record);
    
    int deleteBatch(@Param("phoneIds") List<String> phoneIds,@Param("taskId") String taskId);
    
    int insertBatch(List<TSdktaskPhone> stPhones);
}