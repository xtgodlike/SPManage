package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.controller.TimerTask;
import com.qy.sp.manage.dao.base.BaseDao;
import com.qy.sp.manage.dao.impl.HostDao;
import com.qy.sp.manage.dto.THost;

@Component
public class THostDao extends BaseDao{
	public static final String KEY_CACHE_THOST = "KEY_CACHE_THOST";
	@Resource
	private HostDao hostDao;
	
	@PostConstruct
	public void init(){
		if(!TimerTask.containsClearDailyKey(KEY_CACHE_THOST)){
			TimerTask.putClearDailyKey(KEY_CACHE_THOST);
		}
	}
	
	public int deleteByPrimaryKey(Integer hostId){
		return hostDao.deleteByPrimaryKey(hostId);
	}

	public int insert(THost record){
		return hostDao.insert(record);
	}

	public int insertSelective(THost record){
		return hostDao.insertSelective(record);
	}

	public THost selectByPrimaryKey(Integer hostId){
		return hostDao.selectByPrimaryKey(hostId);
	}
    
	public THost selectByName(String hostName){
		return hostDao.selectByName(hostName);
	}

	public int updateByPrimaryKeySelective(THost record){
		return hostDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(THost record){
		return hostDao.updateByPrimaryKey(record);
	}
    
	public List<THost> getAll(){
		return hostDao.getAll();
	}
}