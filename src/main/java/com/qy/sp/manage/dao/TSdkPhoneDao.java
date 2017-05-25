package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.controller.TimerTask;
import com.qy.sp.manage.dao.base.BaseDao;
import com.qy.sp.manage.dao.impl.SdkPhoneDao;
import com.qy.sp.manage.dto.TSdkPhone;

@Component @MyBatisRepository
public class TSdkPhoneDao extends BaseDao{
	public static final String KEY_CACHE_TSDKPHONE = "KEY_CACHE_TSDKPHONE";
    @Resource 
    private SdkPhoneDao sdkPhoneDao;
    @PostConstruct
	public void init(){
		if(!TimerTask.containsClearDailyKey(KEY_CACHE_TSDKPHONE)){
			TimerTask.putClearDailyKey(KEY_CACHE_TSDKPHONE);
		}
	}
	
    public int deleteByPrimaryKey(String phoneId){
    	redisDao.remove(KEY_CACHE_TSDKPHONE, phoneId);
    	return sdkPhoneDao.deleteByPrimaryKey(phoneId);
    }

    public int insert(TSdkPhone record){
    	return sdkPhoneDao.insert(record);
    }

    public int insertSelective(TSdkPhone record){
    	return sdkPhoneDao.insertSelective(record);
    }

    public TSdkPhone selectByPrimaryKey(String phoneId){
    	return sdkPhoneDao.selectByPrimaryKey(phoneId);
    }

    public int updateByPrimaryKeySelective(TSdkPhone record){
    	redisDao.remove(KEY_CACHE_TSDKPHONE, record.getPhoneId());
    	return sdkPhoneDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(TSdkPhone record){
    	redisDao.remove(KEY_CACHE_TSDKPHONE, record.getPhoneId());
    	return sdkPhoneDao.updateByPrimaryKey(record);
    }
    
    public List<String> getPhoneIdByIMEIs(List<String> imeis){
    	return sdkPhoneDao.getPhoneIdByIMEIs(imeis);
    }
}