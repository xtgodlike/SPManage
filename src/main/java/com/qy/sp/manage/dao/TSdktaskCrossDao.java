package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.controller.TimerTask;
import com.qy.sp.manage.dao.base.BaseDao;
import com.qy.sp.manage.dao.impl.SdktaskCrossDao;
import com.qy.sp.manage.dto.TSdktaskCross;
import com.qy.sp.manage.dto.TSdktaskCrossKey;

@Component
public class TSdktaskCrossDao extends BaseDao{
	public static final String KEY_CACHE_TSDKTASK = "KEY_CACHE_TSDKTASK";
	@Resource
	private SdktaskCrossDao sdktaskCrossDao;
	@PostConstruct
	public void init(){
		if(!TimerTask.containsClearDailyKey(KEY_CACHE_TSDKTASK)){
			TimerTask.putClearDailyKey(KEY_CACHE_TSDKTASK);
		}
	}
	
	public int deleteByPrimaryKey(TSdktaskCrossKey key){
    	redisDao.clear(KEY_CACHE_TSDKTASK);
		return sdktaskCrossDao.deleteByPrimaryKey(key);
	}

	public int insert(TSdktaskCross record){
		redisDao.clear(KEY_CACHE_TSDKTASK);
		return sdktaskCrossDao.insert(record);
	}

    public int insertSelective(TSdktaskCross record){
    	redisDao.clear(KEY_CACHE_TSDKTASK);
    	return sdktaskCrossDao.insertSelective(record);
    }

   public TSdktaskCross selectByPrimaryKey(TSdktaskCrossKey key){
	   return sdktaskCrossDao.selectByPrimaryKey(key);
   }

   public int updateByPrimaryKeySelective(TSdktaskCross record){
   		redisDao.clear(KEY_CACHE_TSDKTASK);
	   return sdktaskCrossDao.updateByPrimaryKeySelective(record);
   }

   public int updateByPrimaryKey(TSdktaskCross record){
   		redisDao.clear(KEY_CACHE_TSDKTASK);
	   return sdktaskCrossDao.updateByPrimaryKey(record);
   }
    
   public List<TSdktaskCross> getSdkTaskCrossList(@Param("sdktaskCross") TSdktaskCross sdktaskCross,@Param("start") int start,@Param("end") int end){
	   return sdktaskCrossDao.getSdkTaskCrossList(sdktaskCross, start, end);
   }
    
   public int getSdkTaskCrossItems(TSdktaskCross sdktaskCross){
	   return sdktaskCrossDao.getSdkTaskCrossItems(sdktaskCross);
   }
    
   public List<TSdktaskCross> getByPrimaryKeyForCheck(TSdktaskCrossKey key){
	   return sdktaskCrossDao.getByPrimaryKeyForCheck(key);
   }
}