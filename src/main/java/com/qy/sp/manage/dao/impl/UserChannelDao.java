package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TUserChannelKey;

@Component @MyBatisRepository
public interface UserChannelDao {
	
    int deleteByPrimaryKey(TUserChannelKey key);
    
    int deleteByUserId(String userId);

    int insert(TUserChannelKey record);

    int insertSelective(TUserChannelKey record);
    
    List<TUserChannelKey> getUserChannels(String userId);
    
    List<String> getChannelIdsByUserId(String userId);
}