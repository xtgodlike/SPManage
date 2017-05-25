package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TChannelPiple;
import com.qy.sp.manage.dto.TChannelPipleKey;

@Component @MyBatisRepository
public interface ChannelPipleDao {
    int deleteByPrimaryKey(TChannelPipleKey key);

    int insert(TChannelPiple record);

    int insertSelective(TChannelPiple record);

    TChannelPiple selectByPrimaryKey(TChannelPipleKey key);

    int updateByPrimaryKeySelective(TChannelPiple record);

    int updateByPrimaryKey(TChannelPiple record);
    
	List<TChannelPiple> getListByPipleId(String pipleId);
	
	List<TChannelPiple> getListByChannelId(String channelId);
}