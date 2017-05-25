package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TSdkconfigPhone;
import com.qy.sp.manage.dto.TSdkconfigPhoneKey;

@Component @MyBatisRepository
public interface SdkconfigPhoneDao {
    int deleteByPrimaryKey(TSdkconfigPhoneKey key);

    int insert(TSdkconfigPhone record);

    int insertSelective(TSdkconfigPhone record);

    TSdkconfigPhone selectByPrimaryKey(TSdkconfigPhoneKey key);

    int updateByPrimaryKeySelective(TSdkconfigPhone record);

    int updateByPrimaryKey(TSdkconfigPhone record);
    
    int deleteByConfigId(String configId);
    
    int deleteBatch(@Param("phoneIds") List<String> phoneIds,@Param("configId") String configId);
    
    int insertBatch(List<TSdkconfigPhone> scPhones);
}