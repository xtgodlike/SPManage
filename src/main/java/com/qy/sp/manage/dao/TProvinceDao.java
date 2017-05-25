package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.controller.TimerTask;
import com.qy.sp.manage.dao.base.BaseDao;
import com.qy.sp.manage.dao.impl.ProvinceDao;
import com.qy.sp.manage.dto.TProvince;

@Component 
public class TProvinceDao extends BaseDao{
	public static final String KEY_CACHE_TPROVINCE = "KEY_CACHE_TPROVINCE";
	@Resource
	private ProvinceDao provinceDao;
	@PostConstruct
	public void init(){
		if(!TimerTask.containsClearDailyKey(KEY_CACHE_TPROVINCE)){
			TimerTask.putClearDailyKey(KEY_CACHE_TPROVINCE);
		}
	}
	
    public int deleteByPrimaryKey(Integer provinceId){
    	return provinceDao.deleteByPrimaryKey(provinceId);
    }

    public int insert(TProvince record){
    	return provinceDao.insert(record);
    }

    public int insertSelective(TProvince record){
    	return provinceDao.insertSelective(record);
    }

   public TProvince selectByPrimaryKey(Integer provinceId){
	   return provinceDao.selectByPrimaryKey(provinceId);
   }
    
   public TProvince selectByProvinceName(String provinceName){
	   return provinceDao.selectByProvinceName(provinceName);
   }

   public int updateByPrimaryKeySelective(TProvince record){
	   redisDao.remove(KEY_CACHE_TPROVINCE, record.getProvinceName());
	   return provinceDao.updateByPrimaryKeySelective(record);
   }

   public int updateByPrimaryKey(TProvince record){
	   redisDao.remove(KEY_CACHE_TPROVINCE, record.getProvinceName());
	   return provinceDao.updateByPrimaryKey(record);
   }
    
   public List<TProvince> getProvinceList(){
	   return provinceDao.getProvinceList();
   }
    
  public List<TProvince> getAll(){
	  return provinceDao.getAll();
  }
}