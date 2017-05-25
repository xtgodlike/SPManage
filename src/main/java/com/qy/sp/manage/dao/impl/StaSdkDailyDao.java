package com.qy.sp.manage.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.StaSdkDailySearchParam;
import com.qy.sp.manage.dto.TStaSdkDaily;
import com.qy.sp.manage.dto.TStaSdkDailyKey;

@Component @MyBatisRepository
public interface StaSdkDailyDao {
    int deleteByPrimaryKey(TStaSdkDailyKey key);

    int insert(TStaSdkDaily record);

    int insertSelective(TStaSdkDaily record);

    TStaSdkDaily selectByPrimaryKey(TStaSdkDailyKey key);

    int updateByPrimaryKeySelective(TStaSdkDaily record);

    int updateByPrimaryKey(TStaSdkDaily record);
    
    int insertBatch(List<TStaSdkDaily> sdkDailies);
    
    List<TStaSdkDaily> getStaSdkDailyList(@Param("param") StaSdkDailySearchParam param);
    
    int deleteByDate(Date date);
    
}