package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.impl.UserChannelDao;
import com.qy.sp.manage.dto.TUserChannelKey;

@Component
public class TUserChannelDao{
	@Resource
	private UserChannelDao userChannelDao;
	
    public int deleteByPrimaryKey(TUserChannelKey key){
    	return userChannelDao.deleteByPrimaryKey(key);
    }
    
    public int deleteByUserId(String userId){
    	return userChannelDao.deleteByUserId(userId);
    }

    public int insert(TUserChannelKey record){
    	return userChannelDao.insert(record);
    }

    public int insertSelective(TUserChannelKey record){
    	return userChannelDao.insertSelective(record);
    }
    
    public List<TUserChannelKey> getUserChannels(String userId){
    	return userChannelDao.getUserChannels(userId);
    }
    
    public List<String> getChannelIdsByUserId(String userId){
    	return userChannelDao.getChannelIdsByUserId(userId);
    }
}