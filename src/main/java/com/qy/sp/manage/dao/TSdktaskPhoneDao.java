package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.common.utils.StringUtil;
import com.qy.sp.manage.controller.TimerTask;
import com.qy.sp.manage.dao.base.BaseDao;
import com.qy.sp.manage.dao.impl.SdktaskPhoneDao;
import com.qy.sp.manage.dto.TSdktaskPhone;
import com.qy.sp.manage.dto.TSdktaskPhoneKey;

@Component @MyBatisRepository
public class TSdktaskPhoneDao extends BaseDao{
	public static final String KEY_CACHE_TSDKTASK = "KEY_CACHE_TSDKTASK";
	@Resource
	private SdktaskPhoneDao sdktaskPhoneDao;
	@PostConstruct
	public void init(){
		if(!TimerTask.containsClearDailyKey(KEY_CACHE_TSDKTASK)){
			TimerTask.putClearDailyKey(KEY_CACHE_TSDKTASK);
		}
	}
	
	public int deleteByPrimaryKey(TSdktaskPhoneKey key){
		redisDao.clear(KEY_CACHE_TSDKTASK);
		return sdktaskPhoneDao.deleteByPrimaryKey(key);
	}

	public int insert(TSdktaskPhone record){
		redisDao.clear(KEY_CACHE_TSDKTASK);
		return sdktaskPhoneDao.insert(record);
	}

	public int insertSelective(TSdktaskPhone record){
		redisDao.clear(KEY_CACHE_TSDKTASK);
		return sdktaskPhoneDao.insertSelective(record);
	}

	public TSdktaskPhone selectByPrimaryKey(TSdktaskPhoneKey key){
		return sdktaskPhoneDao.selectByPrimaryKey(key);
	}

	public int updateByPrimaryKeySelective(TSdktaskPhone record){
		redisDao.clear(KEY_CACHE_TSDKTASK);
		return sdktaskPhoneDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TSdktaskPhone record){
		redisDao.clear(KEY_CACHE_TSDKTASK);
		return sdktaskPhoneDao.updateByPrimaryKey(record);
	}
    
	public int deleteBatch(@Param("phoneIds") List<String> phoneIds,@Param("taskId") String taskId){
		redisDao.clear(KEY_CACHE_TSDKTASK);
		return sdktaskPhoneDao.deleteBatch(phoneIds, taskId);
	}
    
	public int insertBatch(List<TSdktaskPhone> stPhones){
		return sdktaskPhoneDao.insertBatch(stPhones);
	}
}