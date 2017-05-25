package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.controller.TimerTask;
import com.qy.sp.manage.dao.base.BaseDao;
import com.qy.sp.manage.dao.impl.SdkconfigPhoneDao;
import com.qy.sp.manage.dto.TSdkconfigPhone;
import com.qy.sp.manage.dto.TSdkconfigPhoneKey;

@Component
public class TSdkconfigPhoneDao extends BaseDao{
	public static final String KEY_CACHE_TSDKCONFIG = "KEY_CACHE_TSDKCONFIG";
	@Resource
	private SdkconfigPhoneDao sdkconfigPhoneDao;
	@PostConstruct
	public void init(){
		if(!TimerTask.containsClearDailyKey(KEY_CACHE_TSDKCONFIG)){
			TimerTask.putClearDailyKey(KEY_CACHE_TSDKCONFIG);
		}
	}
	
    public int deleteByPrimaryKey(TSdkconfigPhoneKey key){
    	redisDao.clear(KEY_CACHE_TSDKCONFIG+key.getConfigId());
    	return sdkconfigPhoneDao.deleteByPrimaryKey(key);
    }

    public int insert(TSdkconfigPhone record){
    	redisDao.clear(KEY_CACHE_TSDKCONFIG+record.getConfigId());
    	return sdkconfigPhoneDao.insert(record);
    }

    public int insertSelective(TSdkconfigPhone record){
    	redisDao.clear(KEY_CACHE_TSDKCONFIG+record.getConfigId());
    	return sdkconfigPhoneDao.insertSelective(record);
    }

    public TSdkconfigPhone selectByPrimaryKey(TSdkconfigPhoneKey key){
    	return sdkconfigPhoneDao.selectByPrimaryKey(key);
    }

    public int updateByPrimaryKeySelective(TSdkconfigPhone record){
    	redisDao.clear(KEY_CACHE_TSDKCONFIG+record.getConfigId());
    	return sdkconfigPhoneDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(TSdkconfigPhone record){
    	redisDao.clear(KEY_CACHE_TSDKCONFIG+record.getConfigId());
    	return sdkconfigPhoneDao.updateByPrimaryKey(record);
    }
    
    public int deleteByConfigId(String configId){
    	redisDao.clear(KEY_CACHE_TSDKCONFIG+configId);
    	return sdkconfigPhoneDao.deleteByConfigId(configId);
    }
    
    public int deleteBatch(@Param("phoneIds") List<String> phoneIds,@Param("configId") String configId){
    	redisDao.clear(KEY_CACHE_TSDKCONFIG+configId);
    	return sdkconfigPhoneDao.deleteBatch(phoneIds, configId);
    }
    
    public int insertBatch(List<TSdkconfigPhone> scPhones){
    	return sdkconfigPhoneDao.insertBatch(scPhones);
    }
}