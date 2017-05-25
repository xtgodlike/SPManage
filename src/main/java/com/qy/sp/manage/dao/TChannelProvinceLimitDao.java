package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.controller.TimerTask;
import com.qy.sp.manage.dao.base.BaseDao;
import com.qy.sp.manage.dao.impl.ChannelProvinceLimitDao;
import com.qy.sp.manage.dto.TChannelProvinceLimit;
import com.qy.sp.manage.dto.TChannelProvinceLimitKey;
import com.qy.sp.manage.dto.TPipleProduct;

@Component
public class TChannelProvinceLimitDao extends BaseDao{
public static final String KEY_CACHE_TCHANNELPROVINCELIMIT = "KEY_CACHE_TCHANNELPROVINCELIMIT";
	
	@Resource
	private ChannelProvinceLimitDao channelProvinceLimitDao;
	
	@PostConstruct
	public void init(){
		if(!TimerTask.containsClearDailyKey(KEY_CACHE_TCHANNELPROVINCELIMIT)){
			TimerTask.putClearDailyKey(KEY_CACHE_TCHANNELPROVINCELIMIT);
		}
	}
	
	public int deleteByPrimaryKey(TChannelProvinceLimitKey key){
		redisDao.remove(KEY_CACHE_TCHANNELPROVINCELIMIT, key.getPipleId()+key.getChannelId()+key.getProvinceId());
		return channelProvinceLimitDao.deleteByPrimaryKey(key);
	}

    public int insert(TChannelProvinceLimit record){
    	redisDao.remove(KEY_CACHE_TCHANNELPROVINCELIMIT, record.getPipleId()+record.getChannelId()+record.getProvinceId());
    	return channelProvinceLimitDao.insert(record);
    }

    public int insertSelective(TChannelProvinceLimit record){
    	redisDao.remove(KEY_CACHE_TCHANNELPROVINCELIMIT, record.getPipleId()+record.getChannelId()+record.getProvinceId());
    	return channelProvinceLimitDao.insertSelective(record);
    }

    public TChannelProvinceLimit selectByPrimaryKey(TChannelProvinceLimitKey key){
    	return channelProvinceLimitDao.selectByPrimaryKey(key);
    }

    public int updateByPrimaryKeySelective(TChannelProvinceLimit record){
    	redisDao.remove(KEY_CACHE_TCHANNELPROVINCELIMIT, record.getPipleId()+record.getChannelId()+record.getProvinceId());
    	return channelProvinceLimitDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(TChannelProvinceLimit record){
    	redisDao.remove(KEY_CACHE_TCHANNELPROVINCELIMIT, record.getPipleId()+record.getChannelId()+record.getProvinceId());
    	return channelProvinceLimitDao.updateByPrimaryKey(record);
    }
    
    public List<TChannelProvinceLimit> getListByPipleIdAndChannelId(TChannelProvinceLimitKey key){
    	return channelProvinceLimitDao.getListByPipleIdAndChannelId(key);
    }
    
    public int insertBatch(List<TChannelProvinceLimit> cLimits){
    	for (TChannelProvinceLimit cpl : cLimits) {
			redisDao.remove(KEY_CACHE_TCHANNELPROVINCELIMIT, cpl.getPipleId()+cpl.getChannelId()+cpl.getProvinceId());
		}
    	return channelProvinceLimitDao.insertBatch(cLimits);
    }
    
    public int updateBatch(List<TChannelProvinceLimit> cLimits){
    	for (TChannelProvinceLimit cpl : cLimits) {
			redisDao.remove(KEY_CACHE_TCHANNELPROVINCELIMIT, cpl.getPipleId()+cpl.getChannelId()+cpl.getProvinceId());
		}
    	return channelProvinceLimitDao.updateBatch(cLimits);
    }
}