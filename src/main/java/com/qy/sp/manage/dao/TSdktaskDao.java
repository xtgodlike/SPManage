package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.controller.TimerTask;
import com.qy.sp.manage.dao.base.BaseDao;
import com.qy.sp.manage.dao.impl.SdktaskDao;
import com.qy.sp.manage.dto.TSdktask;

@Component
public class TSdktaskDao extends BaseDao{
	public static final String KEY_CACHE_TSDKTASK = "KEY_CACHE_TSDKTASK";
	@Resource
	private SdktaskDao sdktaskDao;
	@PostConstruct
	public void init(){
		if(!TimerTask.containsClearDailyKey(KEY_CACHE_TSDKTASK)){
			TimerTask.putClearDailyKey(KEY_CACHE_TSDKTASK);
		}
	}
	
	public  int deleteByPrimaryKey(String taskId){
		redisDao.clear(KEY_CACHE_TSDKTASK);
		return sdktaskDao.deleteByPrimaryKey(taskId);
	}

	public int insert(TSdktask record){
		redisDao.clear(KEY_CACHE_TSDKTASK);
		return sdktaskDao.insert(record);
	}

	public int insertSelective(TSdktask record){
		redisDao.clear(KEY_CACHE_TSDKTASK);
		return sdktaskDao.insertSelective(record);
	}

    public TSdktask selectByPrimaryKey(String taskId){
    	return sdktaskDao.selectByPrimaryKey(taskId);
    }

   public int updateByPrimaryKeySelective(TSdktask record){
	   redisDao.clear(KEY_CACHE_TSDKTASK);
	   return sdktaskDao.updateByPrimaryKeySelective(record);
   }

   public int updateByPrimaryKey(TSdktask record){
	   redisDao.clear(KEY_CACHE_TSDKTASK);
	   return sdktaskDao.updateByPrimaryKey(record);
   }
    
   public List<TSdktask> getSdkTaskList(@Param("sdkTask") TSdktask sdkTask,@Param("start") int start,@Param("end") int end){
	   return sdktaskDao.getSdkTaskList(sdkTask, start, end);
   }
    
   public int getSdkTaskItems(TSdktask sdktask){
	   return sdktaskDao.getSdkTaskItems(sdktask);
   }
    
   public TSdktask selectByTaskName(String taskName){
	   return sdktaskDao.selectByTaskName(taskName);
   }
}