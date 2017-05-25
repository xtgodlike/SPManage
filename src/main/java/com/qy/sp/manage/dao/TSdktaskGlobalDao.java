package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.controller.TimerTask;
import com.qy.sp.manage.dao.base.BaseDao;
import com.qy.sp.manage.dao.impl.SdktaskGlobalDao;
import com.qy.sp.manage.dto.TSdktaskGlobal;
import com.qy.sp.manage.dto.TSdktaskGlobalKey;

@Component
public class TSdktaskGlobalDao extends BaseDao{
	@Resource
	private SdktaskGlobalDao sdktaskGlobalDao;
	public static final String KEY_CACHE_TSDKTASK = "KEY_CACHE_TSDKTASK";
	@PostConstruct
	public void init(){
		if(!TimerTask.containsClearDailyKey(KEY_CACHE_TSDKTASK)){
			TimerTask.putClearDailyKey(KEY_CACHE_TSDKTASK);
		}
	}
	
	public int deleteByPrimaryKey(TSdktaskGlobalKey key){
		redisDao.clear(KEY_CACHE_TSDKTASK);
		return sdktaskGlobalDao.deleteByPrimaryKey(key);
	}

	public int insert(TSdktaskGlobal record){
		redisDao.clear(KEY_CACHE_TSDKTASK);
		return sdktaskGlobalDao.insert(record);
	}

	public int insertSelective(TSdktaskGlobal record){
		redisDao.clear(KEY_CACHE_TSDKTASK);
		return sdktaskGlobalDao.insertSelective(record);
	}

	public TSdktaskGlobal selectByPrimaryKey(TSdktaskGlobalKey key){
		return sdktaskGlobalDao.selectByPrimaryKey(key);
	}

	public int updateByPrimaryKeySelective(TSdktaskGlobal record){
		redisDao.clear(KEY_CACHE_TSDKTASK);
		return sdktaskGlobalDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TSdktaskGlobal record){
		redisDao.clear(KEY_CACHE_TSDKTASK);
		return sdktaskGlobalDao.updateByPrimaryKey(record);
	}
    
	public List<TSdktaskGlobal> getSdkTaskGlobalList(@Param("sdktaskGlobal") TSdktaskGlobal sdktaskGlobal,@Param("start") int start,@Param("end") int end){
		return sdktaskGlobalDao.getSdkTaskGlobalList(sdktaskGlobal, start, end);
	}
    
	public int getSdkTaskGlobalItems(TSdktaskGlobal sdktaskGlobal){
		return sdktaskGlobalDao.getSdkTaskGlobalItems(sdktaskGlobal);
	}
    
	public TSdktaskGlobal selectByTaskId(String taskId){
		return sdktaskGlobalDao.selectByTaskId(taskId);
	}
}