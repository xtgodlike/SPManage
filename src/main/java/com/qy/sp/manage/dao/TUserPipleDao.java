package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.impl.UserPipleDao;
import com.qy.sp.manage.dto.TUserPipleKey;

@Component
public class TUserPipleDao {
	@Resource
	private UserPipleDao userPipleDao;
	
	public int deleteByPrimaryKey(TUserPipleKey key){
		return userPipleDao.deleteByPrimaryKey(key);
	}
    
	public int deleteByUserId(String userId){
		return userPipleDao.deleteByUserId(userId);
	}

	public int insert(TUserPipleKey record){
		return userPipleDao.insert(record);
	}

	public int insertSelective(TUserPipleKey record){
		return userPipleDao.insertSelective(record);
	}
    
	public List<TUserPipleKey> getUserPiples(String userId){
		return userPipleDao.getUserPiples(userId);
	}
    
	public List<String> getPipleIdsByUserId(String userId){
		return userPipleDao.getPipleIdsByUserId(userId);
	}
}