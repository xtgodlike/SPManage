package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.controller.TimerTask;
import com.qy.sp.manage.dao.base.BaseDao;
import com.qy.sp.manage.dao.impl.PipleProvinceDao;
import com.qy.sp.manage.dto.TPipleProvince;
import com.qy.sp.manage.dto.TPipleProvinceKey;

@Component
public class TPipleProvinceDao extends BaseDao{
	public static final String KEY_CACHE_TPIPLEPROVINCE = "KEY_CACHE_TPIPLEPROVINCE";
	@Resource
	private PipleProvinceDao pipleProvinceDao;
	
	@PostConstruct
	public void init(){
		if(!TimerTask.containsClearDailyKey(KEY_CACHE_TPIPLEPROVINCE)){
			TimerTask.putClearDailyKey(KEY_CACHE_TPIPLEPROVINCE);
		}
	}
	
	public int deleteByPrimaryKey(TPipleProvinceKey key){
		redisDao.remove(KEY_CACHE_TPIPLEPROVINCE, key.getPipleId()+key.getProvinceId());
		return pipleProvinceDao.deleteByPrimaryKey(key);
	}
    
   public int deleteByPipleId(String pipleId){
	   return pipleProvinceDao.deleteByPipleId(pipleId);
   }

   public int insert(TPipleProvince record){
	   return pipleProvinceDao.insert(record);
   }

   public int insertSelective(TPipleProvince record){
	   return pipleProvinceDao.insertSelective(record);
   }

  public TPipleProvince selectByPrimaryKey(TPipleProvinceKey key){
	  return pipleProvinceDao.selectByPrimaryKey(key);
  }

  public int updateByPrimaryKeySelective(TPipleProvince record){
	  return pipleProvinceDao.updateByPrimaryKeySelective(record);
  }

  public int updateByPrimaryKey(TPipleProvince record){
	  return pipleProvinceDao.updateByPrimaryKey(record);
  }
    
  public int insertBatch(List<TPipleProvince> pipleProvinces){
	  return pipleProvinceDao.insertBatch(pipleProvinces);
  }
    
  public int updateBatch(List<TPipleProvince> pipleProvinces){
		for (TPipleProvince pp : pipleProvinces) {
			redisDao.remove(KEY_CACHE_TPIPLEPROVINCE, pp.getPipleId()+pp.getProvinceId());
		}
	  return pipleProvinceDao.updateBatch(pipleProvinces);
  }
    
  public List<TPipleProvince> getPipleProvinceList(String pipleId){
	  return pipleProvinceDao.getPipleProvinceList(pipleId);
  }
}