package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.controller.TimerTask;
import com.qy.sp.manage.dao.base.BaseDao;
import com.qy.sp.manage.dto.TMobileBlacklist;

@Component
public class TMobileBlacklistDao extends BaseDao{
public static final String KEY_CACHE_TMOBILEBLACKLIST = "KEY_CACHE_TMOBILEBLACKLIST";
	
	@Resource
	private com.qy.sp.manage.dao.impl.MobileBlacklistDao mobileBlacklistDao;
	
	@PostConstruct
	public void init(){
		if(!TimerTask.containsClearDailyKey(KEY_CACHE_TMOBILEBLACKLIST)){
			TimerTask.putClearDailyKey(KEY_CACHE_TMOBILEBLACKLIST);
		}
	}
	public int deleteByPrimaryKey(String mobile){
		redisDao.remove(KEY_CACHE_TMOBILEBLACKLIST, mobile);
		return mobileBlacklistDao.deleteByPrimaryKey(mobile);
	}

	public int insert(TMobileBlacklist record){
		redisDao.remove(KEY_CACHE_TMOBILEBLACKLIST, record.getMobile());
		return mobileBlacklistDao.insert(record);
	}

	public int insertSelective(TMobileBlacklist record){
		redisDao.remove(KEY_CACHE_TMOBILEBLACKLIST, record.getMobile());
		return mobileBlacklistDao.insertSelective(record);
	}

	public TMobileBlacklist selectByPrimaryKey(String mobile){
		return mobileBlacklistDao.selectByPrimaryKey(mobile);
	}

	public int updateByPrimaryKeySelective(TMobileBlacklist record){
		return mobileBlacklistDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TMobileBlacklist record){
		return mobileBlacklistDao.updateByPrimaryKey(record);
	}
    
	public int deleteBatch(@Param("mobiles") List<String> mobiles){
		for (String mobile : mobiles) {
			redisDao.remove(KEY_CACHE_TMOBILEBLACKLIST, mobile);
		}
		return mobileBlacklistDao.deleteBatch(mobiles);
	}
    
   public int insertBatch(List<TMobileBlacklist> mobileBlacklists){
	   for (TMobileBlacklist mBlack : mobileBlacklists) {
			redisDao.remove(KEY_CACHE_TMOBILEBLACKLIST, mBlack.getMobile());
		}
	   return mobileBlacklistDao.insertBatch(mobileBlacklists);
   }
}