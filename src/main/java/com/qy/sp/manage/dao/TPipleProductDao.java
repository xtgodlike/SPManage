package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.controller.TimerTask;
import com.qy.sp.manage.dao.base.BaseDao;
import com.qy.sp.manage.dao.impl.PipleProductDao;
import com.qy.sp.manage.dto.TChannelPiple;
import com.qy.sp.manage.dto.TPipleProduct;
import com.qy.sp.manage.dto.TPipleProductKey;

@Component
public class TPipleProductDao extends BaseDao{
	public static final String KEY_CACHE_TPIPLEPRODUCT = "KEY_CACHE_TPIPLEPRODUCT";
	@Resource
	private PipleProductDao pipleProductDao;
	
	@PostConstruct
	public void init(){
		if(!TimerTask.containsClearDailyKey(KEY_CACHE_TPIPLEPRODUCT)){
			TimerTask.putClearDailyKey(KEY_CACHE_TPIPLEPRODUCT);
		}
	}
	
	public int deleteByPrimaryKey(TPipleProductKey key){
		redisDao.remove(KEY_CACHE_TPIPLEPRODUCT, key.getPipleId()+key.getProductId());
		return pipleProductDao.deleteByPrimaryKey(key);
	}

    public int insert(TPipleProduct record){
    	return pipleProductDao.insert(record);
    }

    public int insertSelective(TPipleProduct record){
    	return pipleProductDao.insertSelective(record);
    }

    public TPipleProduct selectByPrimaryKey(TPipleProductKey key){
    	return pipleProductDao.selectByPrimaryKey(key);
    }

    public int updateByPrimaryKeySelective(TPipleProduct record){
    	redisDao.remove(KEY_CACHE_TPIPLEPRODUCT, record.getPipleId()+record.getProductId());
    	return pipleProductDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(TPipleProduct record){
    	redisDao.remove(KEY_CACHE_TPIPLEPRODUCT, record.getPipleId()+record.getProductId());
    	return pipleProductDao.updateByPrimaryKey(record);
    }
    
    public List<TPipleProduct> getPipleProList(String pipleId){
    	return pipleProductDao.getPipleProList(pipleId);
    }
    
   public  List<TChannelPiple> getChannelPipleList(String pipleId){
	   return pipleProductDao.getChannelPipleList(pipleId);
   }
    
   public int insertBatch(List<TPipleProduct> pipleProducts){
	   return pipleProductDao.insertBatch(pipleProducts);
   }
    
   public int updateBatch(List<TPipleProduct> pipleProducts){
	   for (TPipleProduct pp : pipleProducts) {
			redisDao.remove(KEY_CACHE_TPIPLEPRODUCT, pp.getPipleId()+pp.getProductId());
		}
	   return pipleProductDao.updateBatch(pipleProducts);
   }
}