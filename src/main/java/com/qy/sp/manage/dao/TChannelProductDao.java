package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.controller.TimerTask;
import com.qy.sp.manage.dao.base.BaseDao;
import com.qy.sp.manage.dao.impl.ChannelProductDao;
import com.qy.sp.manage.dto.TChannelProduct;
import com.qy.sp.manage.dto.TChannelProductKey;

@Component
public class TChannelProductDao extends BaseDao{
	public static final String KEY_CACHE_TCHANNELPRODUCT = "KEY_CACHE_TCHANNELPRODUCT";
	@Resource
	private ChannelProductDao channelProductDao;
	
	@PostConstruct
	public void init(){
		if(!TimerTask.containsClearDailyKey(KEY_CACHE_TCHANNELPRODUCT)){
			TimerTask.putClearDailyKey(KEY_CACHE_TCHANNELPRODUCT);
		}
	}
	public int deleteByPrimaryKey(TChannelProductKey key){
		redisDao.remove(KEY_CACHE_TCHANNELPRODUCT, key.getChannelId()+key.getProductId());
		return channelProductDao.deleteByPrimaryKey(key);
	}

	public int insert(TChannelProduct record){
		return channelProductDao.insert(record);
	}

	public int insertSelective(TChannelProduct record){
		return channelProductDao.insertSelective(record);
	}

	public TChannelProduct selectByPrimaryKey(TChannelProductKey key){
		return channelProductDao.selectByPrimaryKey(key);
	}

	public int updateByPrimaryKeySelective(TChannelProduct record){
		redisDao.remove(KEY_CACHE_TCHANNELPRODUCT, record.getChannelId()+record.getProductId());
		return channelProductDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TChannelProduct record){
		redisDao.remove(KEY_CACHE_TCHANNELPRODUCT, record.getChannelId()+record.getProductId());
		return channelProductDao.updateByPrimaryKey(record);
	}
    
	public int insertBatch(List<TChannelProduct> channelProducts){
		return channelProductDao.insertBatch(channelProducts);
	}
}