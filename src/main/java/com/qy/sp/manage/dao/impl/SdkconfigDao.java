package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TSdkconfig;

@Component @MyBatisRepository
public interface SdkconfigDao {
    int deleteByPrimaryKey(String configId);

	int insert(TSdkconfig record);

	int insertSelective(TSdkconfig record);

	TSdkconfig selectByPrimaryKey(String configId);

	int updateByPrimaryKeySelective(TSdkconfig record);

	int updateByPrimaryKey(TSdkconfig record);

    List<TSdkconfig> getSdkConfigList(@Param("sdkconfig") TSdkconfig sdkconfig,@Param("start") int start,@Param("end") int end);
    
    int getSdkConfigItems(TSdkconfig sdkconfig);
    
}