package com.qy.sp.manage.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.controller.TimerTask;
import com.qy.sp.manage.dao.base.BaseDao;
import com.qy.sp.manage.dao.impl.SdkconfigGlobalDao;
import com.qy.sp.manage.dto.TSdkconfigGlobal;

@Component
public class TSdkconfigGlobalDao extends BaseDao{
	public static final String KEY_CACHE_TSDKCONFIG = "KEY_CACHE_TSDKCONFIG";
	@Resource
	private SdkconfigGlobalDao sdkconfigGlobalDao;
	@PostConstruct
	public void init(){
		if(!TimerTask.containsClearDailyKey(KEY_CACHE_TSDKCONFIG)){
			TimerTask.putClearDailyKey(KEY_CACHE_TSDKCONFIG);
		}
	}
	
    public int deleteByPrimaryKey(String configId){
    	redisDao.clear(KEY_CACHE_TSDKCONFIG+configId);
    	return sdkconfigGlobalDao.deleteByPrimaryKey(configId);
    }

    public int insert(TSdkconfigGlobal record){
    	redisDao.clear(KEY_CACHE_TSDKCONFIG+record.getConfigId());
    	return sdkconfigGlobalDao.insert(record);
    }

    public int insertSelective(TSdkconfigGlobal record){
    	redisDao.clear(KEY_CACHE_TSDKCONFIG+record.getConfigId());
    	return sdkconfigGlobalDao.insertSelective(record);
    }

    public TSdkconfigGlobal selectByPrimaryKey(String configId){
    	return sdkconfigGlobalDao.selectByPrimaryKey(configId);
    }

    public int updateByPrimaryKeySelective(TSdkconfigGlobal record){
    	redisDao.clear(KEY_CACHE_TSDKCONFIG+record.getConfigId());
    	return sdkconfigGlobalDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(TSdkconfigGlobal record){
    	redisDao.clear(KEY_CACHE_TSDKCONFIG+record.getConfigId());
    	return sdkconfigGlobalDao.updateByPrimaryKey(record);
    }
}