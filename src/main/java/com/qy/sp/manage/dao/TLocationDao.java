package com.qy.sp.manage.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.controller.TimerTask;
import com.qy.sp.manage.dao.base.BaseDao;
import com.qy.sp.manage.dao.impl.LocationDao;
import com.qy.sp.manage.dto.TLocation;
import com.qy.sp.manage.dto.TLocationKey;

@Component
public class TLocationDao extends BaseDao{
	public static final String KEY_CACHE_TLOCATION = "KEY_CACHE_TLOCATION";
	@Resource
	private LocationDao locationDao;
	@PostConstruct
	public void init(){
		if(!TimerTask.containsClearDailyKey(KEY_CACHE_TLOCATION)){
			TimerTask.putClearDailyKey(KEY_CACHE_TLOCATION);
		}
	}
	
	public int deleteByPrimaryKey(TLocationKey key){
		redisDao.remove(KEY_CACHE_TLOCATION, key.getSegment());
		return locationDao.deleteByPrimaryKey(key);
	}

	public int insert(TLocation record){
		return locationDao.insert(record);
	}

	public int insertSelective(TLocation record){
		return locationDao.insertSelective(record);
	}

	public TLocation selectByPrimaryKey(TLocationKey key){
		return locationDao.selectByPrimaryKey(key);
	}

	public int updateByPrimaryKeySelective(TLocation record){
		redisDao.remove(KEY_CACHE_TLOCATION, record.getSegment());
		return locationDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TLocation record){
		redisDao.remove(KEY_CACHE_TLOCATION, record.getSegment());
		return locationDao.updateByPrimaryKey(record);
	}
}