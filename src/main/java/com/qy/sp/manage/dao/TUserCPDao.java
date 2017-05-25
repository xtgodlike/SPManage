package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.impl.UserCPDao;
import com.qy.sp.manage.dto.TUserCPKey;

@Component
public class TUserCPDao {
	@Resource
	private UserCPDao userCPDao;
	
	public int deleteByPrimaryKey(TUserCPKey key){
		return userCPDao.deleteByPrimaryKey(key);
	}
    
	public int deleteByUserId(String userId){
		return userCPDao.deleteByUserId(userId);
	}

	public int insert(TUserCPKey record){
		return userCPDao.insert(record);
	}

	public int insertSelective(TUserCPKey record){
		return userCPDao.insertSelective(record);
	}
    
	public List<TUserCPKey> getUserCPs(String userId){
		return userCPDao.getUserCPs(userId);
	}
    
	public List<String> getCPIdsByUserId(String userId){
		return userCPDao.getCPIdsByUserId(userId);
	}
}