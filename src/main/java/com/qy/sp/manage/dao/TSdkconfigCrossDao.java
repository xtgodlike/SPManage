package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.controller.TimerTask;
import com.qy.sp.manage.dao.base.BaseDao;
import com.qy.sp.manage.dao.impl.SdkconfigCrossDao;
import com.qy.sp.manage.dto.TSdkconfigCross;
import com.qy.sp.manage.dto.TSdkconfigCrossKey;

@Component
public class TSdkconfigCrossDao extends BaseDao{
	public static final String KEY_CACHE_TSDKCONFIG = "KEY_CACHE_TSDKCONFIG";
	@Resource
	private SdkconfigCrossDao sdkconfigCrossDao;
	@PostConstruct
	public void init(){
		if(!TimerTask.containsClearDailyKey(KEY_CACHE_TSDKCONFIG)){
			TimerTask.putClearDailyKey(KEY_CACHE_TSDKCONFIG);
		}
	}
	
	public int deleteByPrimaryKey(TSdkconfigCrossKey key){
		redisDao.clear(KEY_CACHE_TSDKCONFIG+key.getConfigId());
		return sdkconfigCrossDao.deleteByPrimaryKey(key);
	}

	public int insert(TSdkconfigCross record){
		redisDao.clear(KEY_CACHE_TSDKCONFIG+record.getConfigId());
		return sdkconfigCrossDao.insert(record);
	}

	public int insertSelective(TSdkconfigCross record){
		redisDao.clear(KEY_CACHE_TSDKCONFIG+record.getConfigId());
		return sdkconfigCrossDao.insertSelective(record);
	}

	public TSdkconfigCross selectByPrimaryKey(TSdkconfigCrossKey key){
		return sdkconfigCrossDao.selectByPrimaryKey(key);
	}
	public List<TSdkconfigCross> selectSelective(TSdkconfigCrossKey key){
		return sdkconfigCrossDao.selectSelective(key);
	}
	public int updateByPrimaryKeySelective(TSdkconfigCross record){
		redisDao.clear(KEY_CACHE_TSDKCONFIG+record.getConfigId());
		return sdkconfigCrossDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TSdkconfigCross record){
    	redisDao.clear(KEY_CACHE_TSDKCONFIG+record.getConfigId());
		return sdkconfigCrossDao.updateByPrimaryKey(record);
	}
    
	public List<TSdkconfigCross> getSdkConfigCrossList(@Param("sdkconfigCross") TSdkconfigCross sdkconfigCross,@Param("start") int start,@Param("end") int end){
		return sdkconfigCrossDao.getSdkConfigCrossList(sdkconfigCross, start, end);
	}
    
	public int getSdkConfigCrossItems(TSdkconfigCross sdkconfigCross){
		return sdkconfigCrossDao.getSdkConfigCrossItems(sdkconfigCross);
	}
    
	public int deleteByConfigId(String configId){
		return sdkconfigCrossDao.deleteByConfigId(configId);
	}
    
	public TSdkconfigCross getByPrimaryKeyForCheck(TSdkconfigCrossKey key){
		return sdkconfigCrossDao.getByPrimaryKeyForCheck(key);
	}
}