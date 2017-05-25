package com.qy.sp.manage.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TSdkOperSta;
import com.qy.sp.manage.dto.TSdkOperStaKey;
import com.qy.sp.manage.dto.TStaSdkDaily;

@Component @MyBatisRepository
public interface SdkOperStaDao {
    int deleteByPrimaryKey(TSdkOperStaKey key);

    int insert(TSdkOperSta record);

    int insertSelective(TSdkOperSta record);

    TSdkOperSta selectByPrimaryKey(TSdkOperStaKey key);

    int updateByPrimaryKeySelective(TSdkOperSta record);

    int updateByPrimaryKey(TSdkOperSta record);
    
    int insertBatch(List<TSdkOperSta> sdkOperStas);
    
     List<TStaSdkDaily> getStaSdkDailyData(String dateStr);
     
     int deleteByDate(Date date);
}