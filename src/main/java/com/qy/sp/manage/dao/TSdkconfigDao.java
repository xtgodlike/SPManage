package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.controller.TimerTask;
import com.qy.sp.manage.dao.base.BaseDao;
import com.qy.sp.manage.dao.impl.SdkconfigDao;
import com.qy.sp.manage.dto.TSdkconfig;

@Component @MyBatisRepository
public class TSdkconfigDao extends BaseDao{
	public static final String KEY_CACHE_TSDKCONFIG = "KEY_CACHE_TSDKCONFIG";
	@Resource
	private SdkconfigDao sdkconfigDao;
	@PostConstruct
	public void init(){
		if(!TimerTask.containsClearDailyKey(KEY_CACHE_TSDKCONFIG)){
			TimerTask.putClearDailyKey(KEY_CACHE_TSDKCONFIG);
		}
	}
	
	public int deleteByPrimaryKey(String configId){
		redisDao.remove(KEY_CACHE_TSDKCONFIG, configId);
		return sdkconfigDao.deleteByPrimaryKey(configId);
	}

	public int insert(TSdkconfig record){
		redisDao.clear(KEY_CACHE_TSDKCONFIG+record.getConfigId());
		return sdkconfigDao.insert(record);
	}

	public int insertSelective(TSdkconfig record){
		redisDao.clear(KEY_CACHE_TSDKCONFIG+record.getConfigId());
		return sdkconfigDao.insertSelective(record);
	}

	public TSdkconfig selectByPrimaryKey(String configId){
		return sdkconfigDao.selectByPrimaryKey(configId);
	}

	public int updateByPrimaryKeySelective(TSdkconfig record){
		redisDao.remove(KEY_CACHE_TSDKCONFIG, record.getConfigId());
		return sdkconfigDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TSdkconfig record){
		redisDao.remove(KEY_CACHE_TSDKCONFIG, record.getConfigId());
		return sdkconfigDao.updateByPrimaryKey(record);
	}

	public List<TSdkconfig> getSdkConfigList(@Param("sdkconfig") TSdkconfig sdkconfig,@Param("start") int start,@Param("end") int end){
		return sdkconfigDao.getSdkConfigList(sdkconfig, start, end);
	}
    
	public int getSdkConfigItems(TSdkconfig sdkconfig){
		return sdkconfigDao.getSdkConfigItems(sdkconfig);
	}
    
}