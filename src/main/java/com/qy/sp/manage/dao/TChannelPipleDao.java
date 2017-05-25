package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.controller.TimerTask;
import com.qy.sp.manage.dao.base.BaseDao;
import com.qy.sp.manage.dao.impl.ChannelPipleDao;
import com.qy.sp.manage.dto.TChannelPiple;
import com.qy.sp.manage.dto.TChannelPipleKey;

@Component
public class TChannelPipleDao extends BaseDao{
public static final String KEY_CACHE_TCHANNELPIPLE= "KEY_CACHE_TCHANNELPIPLE";
	
	@Resource
	private ChannelPipleDao channelPipleDao;
	
	@PostConstruct
	public void init(){
		if(!TimerTask.containsClearDailyKey(KEY_CACHE_TCHANNELPIPLE)){
			TimerTask.putClearDailyKey(KEY_CACHE_TCHANNELPIPLE);
		}
	}
	public int deleteByPrimaryKey(TChannelPipleKey key){
		redisDao.remove(KEY_CACHE_TCHANNELPIPLE, key.getChannelId()+key.getPipleId());
		return channelPipleDao.deleteByPrimaryKey(key);
	}

	public int insert(TChannelPiple record){
		return channelPipleDao.insert(record);
	}

	public int insertSelective(TChannelPiple record){
		return channelPipleDao.insertSelective(record);
	}

	public TChannelPiple selectByPrimaryKey(TChannelPipleKey key){
		return channelPipleDao.selectByPrimaryKey(key);
	}

	public int updateByPrimaryKeySelective(TChannelPiple record){
		redisDao.remove(KEY_CACHE_TCHANNELPIPLE, record.getChannelId()+record.getPipleId());
		return channelPipleDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TChannelPiple record){
		redisDao.remove(KEY_CACHE_TCHANNELPIPLE, record.getChannelId()+record.getPipleId());
		return channelPipleDao.updateByPrimaryKey(record);
	}
    
	public List<TChannelPiple> getListByPipleId(String pipleId){
		return channelPipleDao.getListByPipleId(pipleId);
	}
	
	public List<TChannelPiple> getListByChannelId(String channelId){
		return channelPipleDao.getListByChannelId(channelId);
	}
}