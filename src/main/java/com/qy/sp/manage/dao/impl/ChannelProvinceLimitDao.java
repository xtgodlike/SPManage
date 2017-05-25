package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TChannelProvinceLimit;
import com.qy.sp.manage.dto.TChannelProvinceLimitKey;

@Component @MyBatisRepository
public interface ChannelProvinceLimitDao {
    int deleteByPrimaryKey(TChannelProvinceLimitKey key);

    int insert(TChannelProvinceLimit record);

    int insertSelective(TChannelProvinceLimit record);

    TChannelProvinceLimit selectByPrimaryKey(TChannelProvinceLimitKey key);

    int updateByPrimaryKeySelective(TChannelProvinceLimit record);

    int updateByPrimaryKey(TChannelProvinceLimit record);
    
    List<TChannelProvinceLimit> getListByPipleIdAndChannelId(TChannelProvinceLimitKey key);
    
    int insertBatch(List<TChannelProvinceLimit> cLimits);
    
    int updateBatch(List<TChannelProvinceLimit> cLimits);
}