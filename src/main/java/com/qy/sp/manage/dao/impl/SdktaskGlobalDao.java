package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TSdktaskGlobal;
import com.qy.sp.manage.dto.TSdktaskGlobalKey;

@Component @MyBatisRepository
public interface SdktaskGlobalDao {
    int deleteByPrimaryKey(TSdktaskGlobalKey key);

    int insert(TSdktaskGlobal record);

    int insertSelective(TSdktaskGlobal record);

    TSdktaskGlobal selectByPrimaryKey(TSdktaskGlobalKey key);

    int updateByPrimaryKeySelective(TSdktaskGlobal record);

    int updateByPrimaryKey(TSdktaskGlobal record);
    
    List<TSdktaskGlobal> getSdkTaskGlobalList(@Param("sdktaskGlobal") TSdktaskGlobal sdktaskGlobal,@Param("start") int start,@Param("end") int end);
    
    int getSdkTaskGlobalItems(TSdktaskGlobal sdktaskGlobal);
    
    TSdktaskGlobal selectByTaskId(String taskId);
}