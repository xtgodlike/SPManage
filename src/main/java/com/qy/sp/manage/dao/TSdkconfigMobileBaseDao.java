package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.base.BaseDao;
import com.qy.sp.manage.dao.impl.SdkconfigMobileBaseDao;
import com.qy.sp.manage.dto.TSdkconfigMobileBase;

@Component
public class TSdkconfigMobileBaseDao extends BaseDao {
	public static final String KEY_CACHE_TSDKCONFIGMOBILEBASE = "KEY_CACHE_TSDKCONFIGMOBILEBASE";
	@Resource
	private SdkconfigMobileBaseDao sdkconfigMobileBaseDao;
	
    public int deleteByPrimaryKey(TSdkconfigMobileBase record){
    	int result = sdkconfigMobileBaseDao.deleteByPrimaryKey(record);
    	String redisKey  = record.getAppId()+record.getContentId()+record.getCpId()+record.getReleaseChannelId();
    	redisDao.remove(KEY_CACHE_TSDKCONFIGMOBILEBASE, redisKey);
    	return result;
    }

    public int insert(TSdkconfigMobileBase record){
    	redisDao.clear(KEY_CACHE_TSDKCONFIGMOBILEBASE);
    	return sdkconfigMobileBaseDao.insert(record);
    }

    public int insertSelective(TSdkconfigMobileBase record){
    	redisDao.clear(KEY_CACHE_TSDKCONFIGMOBILEBASE);
    	return sdkconfigMobileBaseDao.insertSelective(record);
    }

    public TSdkconfigMobileBase selectByPrimaryKey(TSdkconfigMobileBase record){
    	return sdkconfigMobileBaseDao.selectByPrimaryKey(record);
    }
    
    public List<TSdkconfigMobileBase> selectSelective(TSdkconfigMobileBase record){
    	return sdkconfigMobileBaseDao.selectSelective(record);
    }

    public int updateByPrimaryKeySelective(TSdkconfigMobileBase record){
    	int result = sdkconfigMobileBaseDao.updateByPrimaryKeySelective(record);
    	String redisKey  = record.getAppId()+record.getContentId()+record.getCpId()+record.getReleaseChannelId();
    	redisDao.remove(KEY_CACHE_TSDKCONFIGMOBILEBASE, redisKey);
    	return result ;
    }

    public int updateByPrimaryKey(TSdkconfigMobileBase record){
    	int result = sdkconfigMobileBaseDao.updateByPrimaryKey(record);
    	String redisKey  = record.getAppId()+record.getContentId()+record.getCpId()+record.getReleaseChannelId();
    	redisDao.remove(KEY_CACHE_TSDKCONFIGMOBILEBASE, redisKey);
    	return result;
    }
}