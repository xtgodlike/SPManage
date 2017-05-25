package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TChannel;

@Component @MyBatisRepository
public interface ChannelDao {
    int deleteByPrimaryKey(String channelId);

    int insert(TChannel record);

    int insertSelective(TChannel record);

    TChannel selectByPrimaryKey(String channelId);

    int updateByPrimaryKeySelective(TChannel record);

    int updateByPrimaryKey(TChannel record);
    
    List<TChannel> getAllChannels();
    
    TChannel selectByFullName(String fullName);
    
    List<TChannel> getChannelList(@Param("channel") TChannel channel,@Param("start") int start,@Param("end") int end,@Param("channelIds") List<String> channelIds);
    
    int getChannelItems(@Param("channel") TChannel channel,@Param("channelIds") List<String> channelIds);
    
    List<TChannel> getChannelsByUserId(String userId);
}