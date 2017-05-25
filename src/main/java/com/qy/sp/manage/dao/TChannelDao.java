package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.controller.TimerTask;
import com.qy.sp.manage.dao.base.BaseDao;
import com.qy.sp.manage.dao.impl.ChannelDao;
import com.qy.sp.manage.dto.TChannel;

@Component 
public class TChannelDao extends BaseDao{
	public static final String KEY_CACHE_TCHANNEL= "KEY_CACHE_TCHANNEL";
	public static final String KEY_CACHE_PREFIX_API= "API_";
	public static final String KEY_CACHE_PREFIX_ID= "ID_";
	@Resource
	private ChannelDao channelDao;
	@PostConstruct
	public void init(){
		if(!TimerTask.containsClearDailyKey(KEY_CACHE_TCHANNEL)){
			TimerTask.putClearDailyKey(KEY_CACHE_TCHANNEL);
		}
	}
	public  int deleteByPrimaryKey(String channelId){
		redisDao.remove(KEY_CACHE_TCHANNEL, KEY_CACHE_PREFIX_ID+channelId);
		return channelDao.deleteByPrimaryKey(channelId);
	}

	public int insert(TChannel record){
		return channelDao.insert(record);
	}

	public int insertSelective(TChannel record){
		return channelDao.insertSelective(record);
	}

	public TChannel selectByPrimaryKey(String channelId){
		return channelDao.selectByPrimaryKey(channelId);
	}

	public int updateByPrimaryKeySelective(TChannel record){
		redisDao.remove(KEY_CACHE_TCHANNEL, KEY_CACHE_PREFIX_ID+record.getChannelId());
		return channelDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TChannel record){
		redisDao.remove(KEY_CACHE_TCHANNEL, KEY_CACHE_PREFIX_ID+record.getChannelId());
		return channelDao.updateByPrimaryKey(record);
	}
    
	public List<TChannel> getAllChannels(){
		return channelDao.getAllChannels();
	}
    
	public TChannel selectByFullName(String fullName){
		return channelDao.selectByFullName(fullName);
	}
    
	public List<TChannel> getChannelList(@Param("channel") TChannel channel,@Param("start") int start,@Param("end") int end,@Param("channelIds") List<String> channelIds){
		return channelDao.getChannelList(channel, start, end, channelIds);
	}
    
	public int getChannelItems(@Param("channel") TChannel channel,@Param("channelIds") List<String> channelIds){
		return channelDao.getChannelItems(channel, channelIds);
	}
	
	public List<TChannel> getChannelsByUserId(String userId){
		return channelDao.getChannelsByUserId(userId);
	}
}