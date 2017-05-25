package com.qy.sp.manage.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.controller.TimerTask;
import com.qy.sp.manage.dao.base.BaseDao;
import com.qy.sp.manage.dao.impl.PipleMobileLimitDao;
import com.qy.sp.manage.dto.TPipleMobileLimit;

@Component
public class TPipleMobileLimitDao  extends BaseDao{
	public static final String KEY_CACHE_TPIPLEMOBILELIMIT = "KEY_CACHE_TPIPLEMOBILELIMIT";
	public static final String KEY_CACHE_PREFIX_TPIPLEMOBILELIMIT = "PipleMobileLimit";
	
	@Resource
	private PipleMobileLimitDao pipleMobileLimitDao;
	@PostConstruct
	public void init(){
		if(!TimerTask.containsClearDailyKey(KEY_CACHE_TPIPLEMOBILELIMIT)){
			TimerTask.putClearDailyKey(KEY_CACHE_TPIPLEMOBILELIMIT);
		}
	}
    
	public int deleteByPrimaryKey(String pipleId){
		redisDao.remove(KEY_CACHE_TPIPLEMOBILELIMIT,KEY_CACHE_PREFIX_TPIPLEMOBILELIMIT+pipleId);
		return pipleMobileLimitDao.deleteByPrimaryKey(pipleId);
	}

	public int insert(TPipleMobileLimit record){
		return pipleMobileLimitDao.insert(record);
	}

   public int insertSelective(TPipleMobileLimit record){
	   return pipleMobileLimitDao.insertSelective(record);
   }

   public TPipleMobileLimit selectByPrimaryKey(String pipleId){
	   return pipleMobileLimitDao.selectByPrimaryKey(pipleId);
   }

   public int updateByPrimaryKeySelective(TPipleMobileLimit record){
	   redisDao.remove(KEY_CACHE_TPIPLEMOBILELIMIT,KEY_CACHE_PREFIX_TPIPLEMOBILELIMIT+record.getPipleId());
	   return pipleMobileLimitDao.updateByPrimaryKeySelective(record);
   }

   public int updateByPrimaryKey(TPipleMobileLimit record){
	   redisDao.remove(KEY_CACHE_TPIPLEMOBILELIMIT,KEY_CACHE_PREFIX_TPIPLEMOBILELIMIT+record.getPipleId());
	   return pipleMobileLimitDao.updateByPrimaryKey(record);
   }
}