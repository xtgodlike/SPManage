package com.qy.sp.manage.dao.impl;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TSdkconfigGlobal;

@Component @MyBatisRepository
public interface SdkconfigGlobalDao {
    int deleteByPrimaryKey(String configId);

    int insert(TSdkconfigGlobal record);

    int insertSelective(TSdkconfigGlobal record);

    TSdkconfigGlobal selectByPrimaryKey(String configId);

    int updateByPrimaryKeySelective(TSdkconfigGlobal record);

    int updateByPrimaryKey(TSdkconfigGlobal record);
}