package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TChannelProduct;
import com.qy.sp.manage.dto.TChannelProductKey;

@Component @MyBatisRepository
public interface ChannelProductDao {
    int deleteByPrimaryKey(TChannelProductKey key);

    int insert(TChannelProduct record);

    int insertSelective(TChannelProduct record);

    TChannelProduct selectByPrimaryKey(TChannelProductKey key);

    int updateByPrimaryKeySelective(TChannelProduct record);

    int updateByPrimaryKey(TChannelProduct record);
    
    int insertBatch(List<TChannelProduct> channelProducts);
}